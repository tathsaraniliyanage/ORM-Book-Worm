package lk.ijse.service;

import lk.ijse.dto.UserDTO;
import lk.ijse.entity.enumuretion.TypeUser;


public interface UserService extends CrudService<UserDTO> {
    UserDTO getUser(Long id);

    UserDTO auth(String password, String username);
}
