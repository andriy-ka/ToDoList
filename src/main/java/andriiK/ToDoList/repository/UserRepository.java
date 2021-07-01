package andriiK.ToDoList.repository;

import andriiK.ToDoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
    Optional<User> findByLogin(String userName);

}
