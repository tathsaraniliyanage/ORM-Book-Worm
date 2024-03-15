package lk.ijse.entity;

import lk.ijse.entity.enumuretion.BorrowingStatus;
import lk.ijse.entity.enumuretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author Praboda Thathsarani
 * @Project BookWorm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "borrowing_detail")
public class BorrowingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Book.class ,fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Borrowing.class , fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Borrowing borrowing;

    @Enumerated(EnumType.STRING)
    private BorrowingStatus status;

}

