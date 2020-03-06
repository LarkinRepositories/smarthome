package ru.innopolis.zuul.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
    @Column(name="name")
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> users;

}
