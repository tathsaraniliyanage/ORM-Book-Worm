package lk.ijse.dto;

import lk.ijse.entity.enumuretion.Status;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class BookDTO {
    private Long id;
    private String name;
    private String author;
    private String genre;
    private LocalDate register_date;
    private Status status;
    private BranchDTO branchDTO;
}
