package deviceService.mapper;

import deviceService.model.BaseEntity;
import dto.base.BaseEntityDto;

public interface Mapper<E extends BaseEntity, D extends BaseEntityDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
