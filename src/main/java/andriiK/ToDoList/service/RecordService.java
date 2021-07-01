package andriiK.ToDoList.service;

import andriiK.ToDoList.model.Record;
import andriiK.ToDoList.model.User;
import andriiK.ToDoList.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService{
    private RecordRepository repository;

    @Autowired
    RecordService (RecordRepository repository){
        this.repository = repository;
    }

    public List<Record> findAllRecordsByUser(User user) {
        return repository.findAllRecordsByUser(user);
    }

    public void saveRecord(Record record){
        repository.save(record);
    }

    public void deleteRecordById(int id){
        repository.deleteRecordById(id);
    }

    public Record findRecordById(int id){
        return repository.findRecordById(id);
    }
}
