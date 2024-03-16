package lk.ijse.service;

import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.projection.NotReturnUsers;

import java.util.List;



public interface BorrowingService extends CrudService<BorrowingDTO> {
    boolean booking(BorrowingDTO borrowingDTO, List<BookDTO> pendingToUpdateBookDTOs);
    public List<NotReturnUsers> getNotReturnUsers();

    List<NotReturnUsers> getNotReturnUsersSearch(String searchText);
}
