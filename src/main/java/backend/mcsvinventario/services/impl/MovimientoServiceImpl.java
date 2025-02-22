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
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
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
    public List<MovimientoDtoResponse> listarMovimientosPorProducto(Integer idProducto) {
        verificarId(idProducto);
        List<Movimiento> movimientos = movimientoRepository.findByProductoId(idProducto);
        if (!movimientos.isEmpty()) {
            return movimientoMapper.toListDto(movimientos);
        }
        return List.of();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public MovimientoDtoResponse registrarMovimiento(MovimientoDtoRequest dto) {
        final Integer cantidad = dto.cantidad();
        final String tipoMovimiento = dto.tipoMovimiento();
        final Integer productoId = dto.productoId();

        sonDatosValidos(cantidad, tipoMovimiento, productoId);
        ProductoDtoResponse producto = obtenerProducto(productoId);
        verificarStock(producto.stock(), tipoMovimiento);

        productoClient.actualizarStock(productoId, cantidad, tipoMovimiento);

        Movimiento movimiento = movimientoMapper.toEntity(dto);
        movimiento.setFechaRegistro(LocalDateTime.now());
        return movimientoMapper.toDto(movimientoRepository.save(movimiento));
    }

    private void verificarStock(Integer stock, String tipoMovimiento) {
        if (Objects.equals(tipoMovimiento, TipoMovimiento.SALIDA.name()) && stock <= 0) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_SIN_STOCK);
        }
    }

    private ProductoDtoResponse obtenerProducto(Integer productoId) {
        Optional<ProductoDtoResponse> producto = Optional.ofNullable(productoClient.obtenerProducto(productoId));
        if (producto.isEmpty()) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_PRODUCTO_INVALIDO);
        }
        return producto.get();
    }

    private void verificarId(Integer id) {
        if (id == null || id <= 0) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_ID_INVALIDO);
        }
    }

    private void sonDatosValidos(Integer cantidad, String tipoMovimiento, Integer idProducto) {
        if (cantidad == null || cantidad <= 0) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_CANTIDAD_INVALIDA);
        }

        if (tipoMovimiento == null || tipoMovimiento.isBlank()) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_TIPO_INVALIDO);
        }

        verificarId(idProducto);
    }
}
