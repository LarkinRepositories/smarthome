package deviceService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device extends BaseEntity {
    @Column(name = "name")
    private String aliasName;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "token")
    private String token;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "device_types",
            joinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private List<Type> types;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_commands",
               joinColumns = {@JoinColumn(name  = "device_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "command_id", referencedColumnName = "id")})
    private List<Command> commands;
//    @Transient
//    private List<Object> scenarios;



}
