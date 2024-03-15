package lk.ijse.service;

import lk.ijse.dto.BorrowingDetailDTO;



public interface BorrowingDetailService extends CrudService<BorrowingDetailDTO> {
    BorrowingDetailDTO getDetails(Long book_id, Long borrowing_id);
}
