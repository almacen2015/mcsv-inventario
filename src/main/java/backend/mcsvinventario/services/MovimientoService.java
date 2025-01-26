package backend.mcsvinventario.services;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.models.dtos.MovimientoDtoResponse;

public interface MovimientoService {

    MovimientoDtoResponse registrarMovimiento(MovimientoDtoRequest dto);
}
