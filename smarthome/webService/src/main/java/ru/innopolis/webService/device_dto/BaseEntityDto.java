package ru.innopolis.webService.device_dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntityDto {
    private Long id;
//    @JsonIgnore
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private String created;
//    @JsonIgnore
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private String updated;
//    @JsonIgnore
//    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private String status;

}
