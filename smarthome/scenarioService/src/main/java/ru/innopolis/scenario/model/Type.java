package ru.innopolis.scenario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class Type extends BaseEntity {
    @Column(name = "name")
    private String name;

}
