package andriy.todolist.service;

import andriy.todolist.model.Record;
import andriy.todolist.model.User;
import andriy.todolist.repository.RecordRepository;
import andriy.todolist.service.RecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

    private RecordService recordService;
    @Mock
    private RecordRepository recordRepository;

    @Before
    public void setUp() {
        this.recordService = new RecordService(recordRepository);
    }

    @Test
    public void findRecordByIdShouldReturnTrue() {
        given(recordRepository.findRecordById(0))
                .willReturn(new Record(0, "0", "0", "0", new User()));

        assertThat(recordService.findRecordById(0)).isEqualTo(new Record(0, "0", "0", "0", new User()));
    }

    @Test
    public void findAllRecordsByUserShouldReturnTrue(){
        User user = new User(0, "Andriy", "andriy@gmail.com", "1234", null);
        List<Record> recordList = Arrays.asList(
                new Record(0, "0", "0", "0", user),
                new Record(1, "1", "1", "1", user));

        given(recordRepository.findAllRecordsByUser(user))
                .willReturn(recordList);

        assertThat(recordService.findAllRecordsByUser(user)).isEqualTo(recordList);
    }
}
