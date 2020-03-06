package deviceService.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "commands")
@Data
public class Command extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "command_id")
    private Long commandId;
//    @ManyToMany(mappedBy = "commands", fetch = FetchType.LAZY)
//    List<Device> devices;
//    @OneToMany(mappedBy = "command")
//    List<Signature> signatures;

}
