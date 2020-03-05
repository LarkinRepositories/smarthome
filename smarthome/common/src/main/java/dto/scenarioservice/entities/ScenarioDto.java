package dto.scenarioservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioDto extends BaseEntityDto {
    private String aliasName;
    private String[] types;
    private long userId;
    private long deviceId;
    private long commandId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime runTime;

}
