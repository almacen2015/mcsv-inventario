package backend.mcsvinventario.services;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;

public interface MovimientoService {

    void registrarMovimiento(MovimientoDtoRequest dto);
}
