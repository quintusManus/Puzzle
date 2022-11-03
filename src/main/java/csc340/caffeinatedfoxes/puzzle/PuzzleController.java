package csc340.caffeinatedfoxes.puzzle;

import csc340.caffeinatedfoxes.puzzle.user.User;
import csc340.caffeinatedfoxes.puzzle.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Stuart Bridges
 */
@Controller
public class PuzzleController {
 
    @Autowired
    private UserRepository userRepo;
     
    @GetMapping("")
    public String index() {
        return "index";
    }
    
    
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    
    //Start of Create, Read, Update, Delete (CRUD) for Actor Climber
    
    
    
    //Actor - Climber / Use Case - Create Account
    @PostMapping("/process_register")
    public String processRegister(User user) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success";
    }
    
    
    //Actor - Climber / Use Case - Log In  
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
                
                User last = listUsers.get(listUsers.size() - 1);
		
		return "users";
	}
        
        @GetMapping("/login")
        public String login(Model model) {
            model.addAttribute("user", new User());
            return "login";
        }
}
