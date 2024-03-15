package lk.ijse.projection;

import lk.ijse.entity.enumuretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BookProjection {
    private Long id;
    private String Name;
    private String Author;
    private String Genre;
    private LocalDate Date;
    private Status Status;
    private String Branch;
}
