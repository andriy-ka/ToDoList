package andriy.todolist.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String login;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Record> records;

}
