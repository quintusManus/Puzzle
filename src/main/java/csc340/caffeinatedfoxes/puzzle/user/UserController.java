package csc340.caffeinatedfoxes.puzzle.user;

/**
 *
 * @author Stuart Bridges
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id){
        service.deleteUser(id);
        return "redirect:/users";
    }
}
