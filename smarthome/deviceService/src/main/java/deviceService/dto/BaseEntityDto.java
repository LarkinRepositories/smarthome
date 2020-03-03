package deviceService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import deviceService.model.Status;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BaseEntityDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updated;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private Status status;

}
