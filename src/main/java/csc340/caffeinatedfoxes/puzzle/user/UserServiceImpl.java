package csc340.caffeinatedfoxes.puzzle.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * A class for serving queries from the database.
 * @author sdbridges
 * Last Updated: 11/15/2022
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional <User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public void save(User employee) {
        userRepository.save(employee);
    }
    
    @Override
    public List <User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
