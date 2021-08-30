package andriy.todolist.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "records")
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode

public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int id;
    private String title;
    private String description;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
