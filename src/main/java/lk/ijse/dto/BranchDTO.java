package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
public class BranchDTO {
    private Long id;
    private String branch;
    private String location;
    private String contact;
    private LocalDate date;

}
