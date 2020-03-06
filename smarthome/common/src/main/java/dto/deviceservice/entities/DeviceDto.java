package dto.deviceservice.entities;

import dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto extends BaseEntityDto {

     private String aliasName;
     private String ip;
     private Integer port;
     private Long userId;
     private String token;
     private Boolean operating;
     private String[] types;
     private String[] commands;

}
