package deviceService.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "types")
@Data
public class Type extends BaseEntity {
    @Column(name = "name")
    private String name;
}
