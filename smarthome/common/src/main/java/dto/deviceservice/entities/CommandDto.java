package dto.deviceservice.entities;

import dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandDto extends BaseEntityDto {
    private String name;
    private Long commandId;
    List<Long> devicesIds;
}
