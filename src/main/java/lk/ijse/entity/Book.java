package lk.ijse.entity;

import lk.ijse.entity.enumuretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(45)", nullable = false)
    private String name;
    @Column(name = "author", columnDefinition = "VARCHAR(45)", nullable = false)
    private String author;

    @Column(name = "genre", columnDefinition = "VARCHAR(45)", nullable = false)
    private String genre;

    @Column(name = "register_date", columnDefinition = "DATE", nullable = false)
    private LocalDate register_date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Branch.class,fetch = FetchType.EAGER)
    private Branch branch;

   /* @OneToMany(mappedBy = "book",targetEntity = BorrowingDetail.class,fetch = FetchType.LAZY)
    private List<BorrowingDetail> borrowingDetails = new ArrayList<>();*/
}
