package backend.mcsvinventario.services.impl;

import backend.mcsvinventario.models.dtos.MovimientoDtoRequest;
import backend.mcsvinventario.repositories.MovimientoRepository;
import backend.mcsvinventario.services.MovimientoService;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public void registrarMovimiento(MovimientoDtoRequest dto) {

    }
}
