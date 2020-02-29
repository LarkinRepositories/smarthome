package deviceService.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


public enum Type {
    REST_DEVICE,
    MQTT_DEVICE
}
