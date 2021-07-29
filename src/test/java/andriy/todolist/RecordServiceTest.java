package andriy.todolist;

import andriy.todolist.model.Record;
import andriy.todolist.model.User;
import andriy.todolist.repository.RecordRepository;
import andriy.todolist.service.RecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

    private RecordService recordService;
    @Mock
    private RecordRepository recordRepository;

    @Before
    public void setUp(){
        this.recordService = new RecordService(recordRepository);
    }

    @Test
    public void findRecordByIdShouldReturnTrue(){
        given(recordRepository.findRecordById(0))
                .willReturn(new Record(0, "0", "0", "0", new User()));

        assertThat(recordService.findRecordById(0)).isEqualTo(new Record(0, "0", "0", "0", new User())).isNotNull();
        assertThat(recordService.findRecordById(0)).isNotNull();
    }
}
