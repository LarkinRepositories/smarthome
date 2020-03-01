package ru.innopolis.scenario.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "scenario")
@Data
public class Scenario extends BaseEntity{
    @Column(name = "name")
    private String aliasName;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "device_id")
    private long deviceId;
    @Column(name = "command_id")
    private long commandId;
    @Column(name = "run_time")
    private Date time;

}
