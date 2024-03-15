package lk.ijse.service;

import lk.ijse.dto.UserDTO;



public interface UserService extends CrudService<UserDTO> {
    UserDTO getUser(Long id);
}
