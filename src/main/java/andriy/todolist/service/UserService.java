package andriy.todolist.service;

import andriy.todolist.exeption.InvalidInputDataException;
import andriy.todolist.exeption.UserAlreadyExistException;
import andriy.todolist.model.User;
import andriy.todolist.model.UserData;
import andriy.todolist.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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

    public void register(UserData userData, BindingResult bindingResult) throws UserAlreadyExistException {

        if (bindingResult.hasErrors()) {
            throw new InvalidInputDataException("Invalid input data, try again");
        }

        //Let's check if user already registered with us
        if (checkIfUserExistByLogin(userData.getLogin())) {
            throw new UserAlreadyExistException("User already exists for this login");
        } else if (checkIfUserExistByEmail(userData.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User user = new User();
        BeanUtils.copyProperties(userData, user);
        encodePassword(user, userData);
        userRepository.save(user);
    }

    public boolean checkIfUserExistByLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public boolean checkIfUserExistByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private void encodePassword(User user, UserData userData) {
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
    }

}
