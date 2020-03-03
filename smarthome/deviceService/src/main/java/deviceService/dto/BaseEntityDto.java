package deviceService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import deviceService.model.Status;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BaseEntityDto {
    private Long id;
//    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime created;
//    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updated;
//    @JsonIgnore
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private Status status;

}
