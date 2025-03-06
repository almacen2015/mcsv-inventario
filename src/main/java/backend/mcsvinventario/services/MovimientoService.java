package backend.mcsvinventario.services;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;

import java.util.List;

public interface MovimientoService {

    MovimientoDtoResponse add(MovimientoDtoRequest dto);

    List<MovimientoDtoResponse> listByIdProducto(Integer idProducto);
}
