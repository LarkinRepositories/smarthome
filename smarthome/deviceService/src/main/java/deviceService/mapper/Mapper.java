package deviceService.mapper;

import deviceService.dto.BaseEntityDto;
import deviceService.model.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends BaseEntityDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
