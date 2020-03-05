package ru.innopolis.webService.device_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto extends BaseEntityDto {
    private String name;
    private List<Long> devicesIds;
}
