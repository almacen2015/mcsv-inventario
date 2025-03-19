package backend.mcsvmovimiento.models.dtos;

public record MovimientoDtoRequest(
        Integer productoId,
        Integer cantidad,
        String tipoMovimiento) {
}

