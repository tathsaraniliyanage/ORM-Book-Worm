package lk.ijse.entity;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "borrowing_date", columnDefinition = "DATE", nullable = false)
    private LocalDate borrowing_date;
    @Column(name = "received_date", columnDefinition = "DATE", nullable = false)
    private LocalDate received_date;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "borrowing",targetEntity = BorrowingDetail.class,fetch = FetchType.LAZY)
    private List<BorrowingDetail> borrowingDetails = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = User.class)
    private User user;

}
