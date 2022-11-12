package csc340.caffeinatedfoxes.puzzle.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container (the objects that form the backbone of your application).

/**
 *
 * @author Stuart Bridges
 */

//CommandLineRunner Interface - used to indicate that a bean should run when it is contained within a SpringApplication
public class UserRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserRunner.class);

    @Autowired
    private UserService userService;
    @Override
    public void run(String...args) throws Exception {

        userService.save(new User("Tom", "Cruise", "tom@gmail.com"));
        userService.save(new User("John", "Cena", "john@gmail.com"));
        userService.save(new User("tony", "stark", "stark@gmail.com"));


        long id1 = 1L;
        userService.findById(id1).ifPresent(System.out::println);

        long id2 = 5L;
        Optional <User> optional = userService.findById(id2);

        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.printf("No user found with id %d%n", id2);
        }

        // get list of employees
        List <User> employees = userService.findAll();
        employees.forEach(employee -> System.out.println(employee.toString()));

        // delete employee by id
        userService.delete(3L);
    }
}
