package deviceService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
@NoArgsConstructor
@Data
public class RestDevice extends Device implements Commander {

    @Override
    public void doCommand() {

    }
}
