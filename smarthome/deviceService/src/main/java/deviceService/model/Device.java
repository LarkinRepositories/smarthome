package deviceService.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
public class Device extends BaseEntity {

    @Column(name = "user_id")
    private long userId;
    @Column(name = "token")
    private String token;
    @ManyToMany
    @JoinTable(name = "device_types",
            joinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id") },
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private List<Type> types;

//    private List<Object> scenarios;
}
