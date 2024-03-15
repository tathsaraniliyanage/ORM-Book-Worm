package lk.ijse.repository;

import lk.ijse.entity.User;



public interface UserRepository extends CrudRepository<User>, Custom<User> {
}
