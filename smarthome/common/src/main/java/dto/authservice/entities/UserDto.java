package dto.authservice.entities;

import dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseEntityDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long chatId;
    private String phone;
    private String[] roles;

}
