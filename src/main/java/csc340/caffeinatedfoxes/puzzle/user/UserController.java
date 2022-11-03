package csc340.caffeinatedfoxes.puzzle.user;

/**
 *
 * @author Stuart Bridges
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;
    
    @GetMapping("/all")
    public String getAllUsers(Model model){
        model.addAttribute("userList", service.getAllUsers());
        return "user";
    }
}
