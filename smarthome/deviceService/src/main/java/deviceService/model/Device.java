package deviceService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "devices")
public class Device extends BaseEntity {

    @Column(name = "name")
    private String aliasName;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "token")
    private String token;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;
//    @Transient
//    private List<Object> scenarios;
//    public void doCommand() {};

}
