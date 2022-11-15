package csc340.caffeinatedfoxes.puzzle.user;

/**
 * The user repository class.
 * @author sdbridges
 * Last Updated: 11/15/2022
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    
    //JPQL Syntax
    
    //Search for Gym
    @Query("SELECT gym FROM User gym WHERE gym.name LIKE %?1% AND gym.type = 'gym'")
    public List<User> search(String keyword);

    Boolean existsByEmail(String email);
    Boolean existsByPassword(String password);

    List<User> findByEmail(String email);
    
}