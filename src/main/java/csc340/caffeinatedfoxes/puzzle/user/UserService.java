package csc340.caffeinatedfoxes.puzzle.user;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Stuart Bridges
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

