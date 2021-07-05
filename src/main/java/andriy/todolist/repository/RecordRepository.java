package andriy.todolist.repository;

import andriy.todolist.model.Record;
import andriy.todolist.model.User;
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
