package lk.ijse.dto;

import lk.ijse.entity.enumuretion.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praboda Thathsarani
 * @Project BookWorm
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {
    private Long id ;
    private String userName;
    private String password;
    private String email;
    private String Address;
    private String Tel;
    private TypeUser user;
}
