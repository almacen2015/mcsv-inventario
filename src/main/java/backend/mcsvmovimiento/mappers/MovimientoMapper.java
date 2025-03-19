package backend.mcsvmovimiento.mappers;

import backend.mcsvmovimiento.models.dtos.MovimientoDtoRequest;
import backend.mcsvmovimiento.models.dtos.MovimientoDtoResponse;
import backend.mcsvmovimiento.models.entities.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovimientoMapper {
    MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);

    @Mapping(target = "id", ignore = true)
    Movimiento toEntity(MovimientoDtoRequest dto);

    MovimientoDtoResponse toDto(Movimiento entity);

    List<MovimientoDtoResponse> toListDto(List<Movimiento> entities);

    List<Movimiento> toListEntity(List<MovimientoDtoRequest> dtos);
}
