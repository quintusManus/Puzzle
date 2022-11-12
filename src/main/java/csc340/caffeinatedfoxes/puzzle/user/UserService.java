package csc340.caffeinatedfoxes.puzzle.user;

/**
 *
 * @author Stuart Bridges
 */
//controller
import java.util.List;
import java.util.Optional;

public interface UserService {
    
    List<User> findAll();
    void save(User user);
    
    Optional<User>findById(Long id);
    void delete(long id);
    
}

