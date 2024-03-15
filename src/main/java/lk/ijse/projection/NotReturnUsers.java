package lk.ijse.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotReturnUsers {
    private String username;
    private String name;
    private LocalDate borrowing_date;
    private LocalDate received_date;
    private String Tel;
}
