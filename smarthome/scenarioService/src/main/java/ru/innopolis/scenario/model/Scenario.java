package ru.innopolis.scenario.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "scenarios")
@Data
public class Scenario extends BaseEntity{
    @Column(name = "name")
    private String aliasName;
    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "scenario_types",
               joinColumns = {@JoinColumn(name = "scenario_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private List<Type> types;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "device_id")
    private long deviceId;
//    @Column(name = "command_id")
//    private long commandId;
    @Column(name = "run_time")
    private LocalDateTime runTime;

}
