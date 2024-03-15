package lk.ijse.repository;

import lk.ijse.dto.BorrowingDetailDTO;
import lk.ijse.entity.BorrowingDetail;



public interface BorrowingDetailRepository extends CrudRepository<BorrowingDetail>, Custom {
    BorrowingDetail getDetails(Long book_id, Long borrowing_id);
}
