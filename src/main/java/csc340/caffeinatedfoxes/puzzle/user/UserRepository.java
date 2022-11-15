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
	//@Query("SELECT u FROM User u WHERE u.email = ?1")
	//public User findByEmail(String email);

    Boolean existsByEmail(String email);
    Boolean existsByPassword(String password);

    List<User> findByEmail(String email);
}