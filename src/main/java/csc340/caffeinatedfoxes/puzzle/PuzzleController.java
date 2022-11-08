package csc340.caffeinatedfoxes.puzzle;

import csc340.caffeinatedfoxes.puzzle.user.User;
import csc340.caffeinatedfoxes.puzzle.user.UserRepository;
import csc340.caffeinatedfoxes.puzzle.api.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Stuart Bridges
 */
@Controller
public class PuzzleController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String index(Model model) throws IOException {
        api.randomQuote();
        String quote = api.quote;
        String author = api.author;

        model.addAttribute("quote", quote);
        model.addAttribute("author", author);
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

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/climber")
    public String climberHomepage(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "climberHomepage";
    }

    @GetMapping("/climber/routes")
    public String climberHomepageRoutes(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "climberHomepageRoutes";
    }

    @GetMapping("/climber/gyms")
    public String climberHomepageGyms(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "climberHomepageGyms";
    }
}
