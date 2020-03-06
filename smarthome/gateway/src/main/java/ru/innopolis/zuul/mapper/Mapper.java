package ru.innopolis.zuul.mapper;


import dto.base.BaseEntityDto;
import ru.innopolis.zuul.model.BaseEntity;


public interface Mapper<E extends BaseEntity, D extends BaseEntityDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
