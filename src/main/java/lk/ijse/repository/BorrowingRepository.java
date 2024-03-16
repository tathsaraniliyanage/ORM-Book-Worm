package lk.ijse.repository;

import lk.ijse.entity.Borrowing;
import lk.ijse.projection.NotReturnUsers;

import java.util.List;



public interface BorrowingRepository extends CrudRepository<Borrowing>, Custom {

    List<NotReturnUsers> getNotReturnUsers();

    List<NotReturnUsers> getNotReturnUsersSearch(String searchText);
}
