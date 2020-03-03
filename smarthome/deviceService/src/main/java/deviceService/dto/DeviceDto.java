package deviceService.dto;

import deviceService.model.Command;
import deviceService.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto extends BaseEntityDto {

     private String aliasName;
     private String ip;
     private Integer port;
     private long userId;
     private String token;
     private List<TypeDto> types;
     private List<CommandDto> commands;

}
