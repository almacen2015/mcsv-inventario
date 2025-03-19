package backend.mcsvmovimiento.services;

import backend.mcsvmovimiento.models.dtos.MovimientoDtoRequest;
import backend.mcsvmovimiento.models.dtos.MovimientoDtoResponse;

import java.util.List;

public interface MovimientoService {

    MovimientoDtoResponse add(MovimientoDtoRequest dto);

    List<MovimientoDtoResponse> listByIdProducto(Integer idProducto);
}
