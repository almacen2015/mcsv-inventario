package backend.mcsvinventario.models.dtos;

public record MovimientoDtoRequest(
        Integer productoId,
        Integer cantidad,
        String tipoMovimiento) {
}

