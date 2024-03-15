package lk.ijse.entity;

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

@Entity
@Table
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "branch", columnDefinition = "VARCHAR(64)", nullable = false)
    private String branch;
    @Column(name = "location", columnDefinition = "TEXT", nullable = false)
    private String location;
    @Column(name = "contact", columnDefinition = "VARCHAR(15)", nullable = false)
    private String contact;
    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

//    @OneToMany(mappedBy = "branch", targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Book> books = new ArrayList<>();

}
