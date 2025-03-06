package backend.mcsvinventario.services.impl;

import backend.mcsvinventario.client.ProductoClient;
import backend.mcsvinventario.exceptions.MovimientoException;
import backend.mcsvinventario.mappers.MovimientoMapper;
import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;
import backend.mcsvinventario.models.dtos.ProductoDtoResponse;
import backend.mcsvinventario.models.entities.Movimiento;
import backend.mcsvinventario.models.entities.TipoMovimiento;
import backend.mcsvinventario.repositories.MovimientoRepository;
import backend.mcsvinventario.services.MovimientoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper = MovimientoMapper.INSTANCE;
    private final ProductoClient productoClient;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, ProductoClient productoClient) {
        this.movimientoRepository = movimientoRepository;
        this.productoClient = productoClient;
    }

    @Override
    public List<MovimientoDtoResponse> listByIdProducto(Integer idProducto) {
        validateId(idProducto);
        List<Movimiento> movimientos = movimientoRepository.findByProductoId(idProducto);
        if (!movimientos.isEmpty()) {
            return movimientoMapper.toListDto(movimientos);
        }
        return List.of();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public MovimientoDtoResponse add(MovimientoDtoRequest dto) {
        final Integer cantidad = dto.cantidad();
        final String tipoMovimiento = dto.tipoMovimiento();
        final Integer productoId = dto.productoId();

        validateData(cantidad, tipoMovimiento, productoId);
        ProductoDtoResponse producto = getProduct(productoId);
        validateStock(producto.stock(), tipoMovimiento);

        productoClient.updateStock(productoId, cantidad, tipoMovimiento);

        Movimiento movimiento = movimientoMapper.toEntity(dto);
        movimiento.setFechaRegistro(LocalDateTime.now());
        return movimientoMapper.toDto(movimientoRepository.save(movimiento));
    }

    private void validateStock(Integer stock, String tipoMovimiento) {
        if (Objects.equals(tipoMovimiento, TipoMovimiento.SALIDA.name()) && stock <= 0) {
            throw new MovimientoException(MovimientoException.MOVEMENT_WITHOUT_STOCK);
        }
    }

    private ProductoDtoResponse getProduct(Integer productoId) {
        Optional<ProductoDtoResponse> producto = Optional.ofNullable(productoClient.getProduct(productoId));
        if (producto.isEmpty()) {
            throw new MovimientoException(MovimientoException.INVALID_PRODUCT);
        }
        return producto.get();
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new MovimientoException(MovimientoException.INVALID_ID);
        }
    }

    private void validateData(Integer cantidad, String tipoMovimiento, Integer idProducto) {
        if (cantidad == null || cantidad <= 0) {
            throw new MovimientoException(MovimientoException.INVALID_AMOUNT);
        }

        if (tipoMovimiento == null || tipoMovimiento.isBlank()) {
            throw new MovimientoException(MovimientoException.INVALID_TYPE_MOVEMENT);
        }

        validateId(idProducto);
    }
}
