package andriiK.ToDoList.repository;

import andriiK.ToDoList.model.Record;
import andriiK.ToDoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findAllRecordsByUser(User user);
    @Transactional
    void deleteRecordById(int id);

    Record findRecordById(int id);
}
