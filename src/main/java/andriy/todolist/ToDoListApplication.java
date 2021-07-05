package andriy.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"andriiK.ToDoList"})
@EntityScan(basePackages = {"andriiK.ToDoList"})
@EnableJpaRepositories(basePackages = {"andriiK.ToDoList"})
public class ToDoListApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

}
