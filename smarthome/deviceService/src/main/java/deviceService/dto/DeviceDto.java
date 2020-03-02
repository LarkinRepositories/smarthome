package deviceService.dto;

import lombok.Data;

@Data
public class DeviceDto {
     private Long id;
     private String aliasName;
     private String ip;
     private String port;
     private String token;
     private Long userId;
}
