package andriy.todolist.repository;

import andriy.todolist.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest(){

        User user = User.builder()
                .email("andriy@gmail.com")
                .login("rock")
                .password("1234")
                .build();

        userRepository.save(user);

        assertThat(user.getId()).isGreaterThan(0);

    }

    @Test
    public void findUserByIdTest(){
        User user = userRepository.findUserById(1);

        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    public void findUserByEmailTest(){
        if(userRepository.findByEmail("andriy@gmail.com").isPresent()) {
            User user = userRepository.findByEmail("andriy@gmail.com").get();

            assertThat(user.getEmail()).isEqualTo("andriy@gmail.com");
        }
    }

    @Test
    public void findUserByLoginTest(){
        if(userRepository.findByLogin("rock").isPresent()) {
            User user = userRepository.findByLogin("rock").get();

            assertThat(user.getLogin()).isEqualTo("rock");
        }
    }

    @Test
    public void updateUserTest(){
        User user = userRepository.getById(1);

        user.setLogin("pop");

        User userUpdated = userRepository.save(user);

        assertThat(userUpdated.getLogin()).isEqualTo("pop");
    }

    @Test
    public void deleteUserTest(){
        User user = userRepository.getById(1);

        userRepository.delete(user);

        User user1 = null;

        Optional<User> optionalUser = userRepository.findByLogin("pop");

        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
        }

        assertThat(user1).isNull();
    }
}
