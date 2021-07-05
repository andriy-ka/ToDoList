package andriy.todolist.service;

import andriy.todolist.exeption.UserAlreadyExistException;
import andriy.todolist.model.User;
import andriy.todolist.model.UserData;
import andriy.todolist.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void register(UserData userData) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if (checkIfUserExist(userData.getLogin())) {
            throw new UserAlreadyExistException("User already exists for this login");
        }
        User user = new User();
        BeanUtils.copyProperties(userData, user);
        encodePassword(user, userData);
        userRepository.save(user);
    }

    public boolean checkIfUserExist(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    private void encodePassword(User user, UserData userData) {
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
    }

}
