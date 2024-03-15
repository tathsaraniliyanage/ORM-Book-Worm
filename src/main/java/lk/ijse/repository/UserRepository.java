package lk.ijse.repository;

import lk.ijse.entity.User;
import lk.ijse.entity.enumuretion.TypeUser;


public interface UserRepository extends CrudRepository<User>, Custom<User> {
    User auth(String password, String username);
}
