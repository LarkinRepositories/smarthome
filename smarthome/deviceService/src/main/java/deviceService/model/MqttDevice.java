package deviceService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "devices")
@Data
public class MqttDevice extends Device {

    public MqttDevice() {
        this.getType().setName("MQTT_DEVICE");
    }
    @Override
    public void doCommand() {
        //implement command logic here, change signature in Device class if needed
    }
}
