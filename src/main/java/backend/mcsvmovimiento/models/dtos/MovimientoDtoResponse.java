package backend.mcsvmovimiento.models.dtos;

public record MovimientoDtoResponse(Integer id,
                                    Integer productoId,
                                    Integer cantidad,
                                    String tipoMovimiento,
                                    String fechaRegistro) {
}
