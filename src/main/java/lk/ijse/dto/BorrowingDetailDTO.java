package lk.ijse.dto;

import lk.ijse.entity.enumuretion.BorrowingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Praboda Thathsarani
 * @Project BookWorm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BorrowingDetailDTO {
    private BookDTO bookDTO;
    private BorrowingDTO borrowingDTO;
    private BorrowingStatus status;

}

