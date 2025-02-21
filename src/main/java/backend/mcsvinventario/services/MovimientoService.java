package backend.mcsvinventario.services;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;

import java.util.List;

public interface MovimientoService {

    MovimientoDtoResponse registrarMovimiento(MovimientoDtoRequest dto);

    List<MovimientoDtoResponse> listarMovimientosPorProducto(Integer idProducto);
}
