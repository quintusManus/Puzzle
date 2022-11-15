package csc340.caffeinatedfoxes.puzzle.user;

import java.util.List;
import java.util.Optional;

/**
 * The user service class.
 * @author sdbridges
 * Last Updated: 11/15/2022
 */

public interface UserService {
    
    //Read
    List<User> findAll();
    Optional<User>findById(Long id);
    
    //Create/Update
    void save(User user);
    
    //Delete
    void delete(long id);
}

