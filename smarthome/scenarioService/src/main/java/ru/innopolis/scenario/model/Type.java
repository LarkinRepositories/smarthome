package ru.innopolis.scenario.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
@Data
public class Type extends BaseEntity {
    @Column(name = "name")
    private String name;

}
