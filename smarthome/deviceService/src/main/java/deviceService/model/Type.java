package deviceService.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "types")
@Data
public class Type extends BaseEntity {
    @Column(name = "name")
    private String name;
//    @ManyToMany(mappedBy = "types", fetch = FetchType.LAZY)
//    private List<Device> devices;
}
