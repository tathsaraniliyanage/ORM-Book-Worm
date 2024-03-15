package lk.ijse.entity;

import lk.ijse.entity.enumuretion.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "username",columnDefinition = "VARCHAR(64)",nullable = false)
    private String username;
    @Column(name = "password",columnDefinition = "VARCHAR(64)",nullable = false)
    private String password;
    @Column(name = "email",columnDefinition = "VARCHAR(64)",nullable = false)
    private String email;
    @Column(name = "Address",columnDefinition = "TEXT",nullable = false)
    private String Address;
    @Column(name = "Tel",columnDefinition = "VARCHAR(15)")
    private String Tel;
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

//    @OneToMany(targetEntity = Borrowing.class ,fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Borrowing>borrowings=new ArrayList<>();

}
