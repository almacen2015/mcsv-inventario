package backend.mcsvinventario.services.impl;

import backend.mcsvinventario.client.ProductoClient;
import backend.mcsvinventario.exceptions.MovimientoException;
import backend.mcsvinventario.mappers.MovimientoMapper;
import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.ProductoDtoResponse;
import backend.mcsvinventario.repositories.MovimientoRepository;
import backend.mcsvinventario.services.MovimientoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Transactional(rollbackOn = Exception.class)
    public void registrarMovimiento(MovimientoDtoRequest dto) {
        final Integer cantidad = dto.cantidad();
        final String tipoMovimiento = dto.tipoMovimiento();
        final Integer productoId = dto.productoId();
        sonDatosValidos(cantidad, tipoMovimiento, productoId);
        verificarExistenciaProducto(productoId);

    }

    private void verificarExistenciaProducto(Integer productoId) {
        Optional<ProductoDtoResponse> producto = Optional.ofNullable(productoClient.obtenerProducto(productoId));
        if (producto.isEmpty()) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_PRODUCTO_INVALIDO);
        }
    }

    private void sonDatosValidos(Integer cantidad, String tipoMovimiento, Integer idProducto) {
        if (cantidad == null || cantidad <= 0) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_CANTIDAD_INVALIDA);
        }

        if (tipoMovimiento == null || tipoMovimiento.isBlank()) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_TIPO_INVALIDO);
        }

        if (idProducto == null || idProducto <= 0) {
            throw new MovimientoException(MovimientoException.MOVIMIENTO_PRODUCTO_INVALIDO);
        }
    }
}
