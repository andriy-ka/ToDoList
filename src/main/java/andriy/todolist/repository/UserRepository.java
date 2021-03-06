package andriy.todolist.repository;

import andriy.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);

    Optional<User> findByLogin(String userName);

    Optional<User> findByEmail(String email);

}
