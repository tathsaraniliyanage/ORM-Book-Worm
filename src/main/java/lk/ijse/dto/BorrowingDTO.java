package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
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

public class BorrowingDTO {

    private Long id;
    private LocalDate borrowing_date;
    private LocalDate received_date;
    private String description;
    private UserDTO userDTO;
    private List<BorrowingDetailDTO> borrowingDetailDTOS = new ArrayList<>();

}
