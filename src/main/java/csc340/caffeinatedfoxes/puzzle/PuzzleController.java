package csc340.caffeinatedfoxes.puzzle;
import csc340.caffeinatedfoxes.puzzle.user.User;
import csc340.caffeinatedfoxes.puzzle.user.UserRepository;
import csc340.caffeinatedfoxes.puzzle.api.api;
import csc340.caffeinatedfoxes.puzzle.ClimbingRoute;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.RouteMatcher.Route;
import org.springframework.web.bind.annotation.*;


@Controller
public class PuzzleController {
 
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RouteRepository repo;
     
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
    
    //Actor - Climber / Use Case - Create Account
    @PostMapping("/process_register")
    public String processRegister(User user) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/climberLogin")
    public String climberLogin(Model model) {
        model.addAttribute("user", new User());
        return "climberLogin";
    }

    @GetMapping("/gymLogin")
    public String gymLogin(Model model) {
        model.addAttribute("user", new User());
        return "gymLogin";
    }

    @RequestMapping("/authenticate")
    public String authenticateUser(@RequestParam(value = "email", required = false) String email) {
        if(userRepo.findByEmail("email")){
            return "redirect:/users";
        }
        return "redirect:/login";
    }


    @GetMapping("/users")
	public String listUsers(Model model) {
            List<User> listUsers = userRepo.findAll();
            model.addAttribute("listUsers", listUsers);
            return "users";
	}
        
        //DELETE
        @GetMapping("/deleteUser/{id}")
        public String deleteUser(@PathVariable(name = "id") Long id) {
            userRepo.deleteById(id);
            return "redirect:/users";
        }

        
        @GetMapping("/climber")
        public String climberHomepage(Model model) {
            return "climberHomepage";
	}
        
        @GetMapping("/climber/routes")
        public String getAllRoutes(Model model) {
            long userID = 1;
            model.addAttribute("routeList", repo.getRoutesByUserID(userID));
            return "climberHomepageRoutes";
        }
        
        @PostMapping("/climber/routes")
        public String deleteRouteByID(@ModelAttribute("route") ClimbingRoute route, Model model) {
            repo.deleteRoute(route.routeID);
            long userID = 1;
            model.addAttribute("routeList", repo.getRoutesByUserID(userID));
            return "climberHomepageRoutes";
        }
        
        @GetMapping("/climber/routes/{id}")
        public String getRouteByID(@PathVariable("id") long routeID, Model model) {
            ClimbingRoute route = repo.getRouteById(routeID);
            model.addAttribute("route", route);
            Attempt attempt = new Attempt();
            model.addAttribute("attempt", attempt);
            model.addAttribute("attemptList", repo.getAllRouteAttempts(routeID));
            return "climberHomepageRoute";
        }
        
        @PostMapping("/climber/routes/{id}")
        public String addRouteAttempt(@PathVariable("id") long routeID, @ModelAttribute("attempt") Attempt attempt, Model model) {
            repo.addRouteAttempt(routeID, attempt.date, attempt.numOfFalls);
            csc340.caffeinatedfoxes.puzzle.ClimbingRoute route = repo.getRouteById(routeID);
            model.addAttribute("route", route);
            model.addAttribute("attemptList", repo.getAllRouteAttempts(routeID));
            return "climberHomepageRoute";
        }
        
        @GetMapping("/climber/routes/edit")
        public String editRoute(@ModelAttribute("route") ClimbingRoute route, Model model) {
            route = repo.getRouteById(route.routeID);
            model.addAttribute("route", route);
            return "climberHomepageEditRoute";
        }
        
        @PostMapping("climber/routes/edit")
        public String saveEditRoute(@ModelAttribute("route") ClimbingRoute route, Model model) {
            repo.editRoute(route.routeID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
            model.addAttribute("route", repo.getRouteById(route.routeID));
            long routeID = route.routeID;
            Attempt attempt = new Attempt();
            model.addAttribute("attempt", attempt);
            model.addAttribute("attemptList", repo.getAllRouteAttempts(routeID));
            return "climberHomepageRoute";
        }
        
        @GetMapping("/climber/routes/add")
        public String addRoute(Model model){
            ClimbingRoute route = new ClimbingRoute();
            model.addAttribute("route", route);
            return "climberHomepageAddRoute";
        }
        
        @PostMapping("/climber/routes/add")
        public String submitRoute(@ModelAttribute("route") ClimbingRoute route, Model model) {
            route.userID = 1;
            repo.addRoute(route.userID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
            model.addAttribute("routeList", repo.getRoutesByUserID(route.userID));
            return "climberHomepageRoutes";
        }
        
        @GetMapping("/climber/gyms")
        public String climberHomepageGyms() {
            return "climberHomepageGyms";
	}
        
        @GetMapping("/gym")
        public String gymHomepage(Model model) {
            long userID = 2;
            model.addAttribute("routeList", repo.getRoutesByUserID(userID));
            return "gymHomepageRoutes";
	}
        
        @GetMapping("/gym/routes")
        public String getAllGymRoutes(Model model) {
            long userID = 2;
            model.addAttribute("routeList", repo.getRoutesByUserID(userID));
            return "gymHomepageRoutes";
        }
        
        @PostMapping("/gym/routes")
        public String deleteGymRouteByID(@ModelAttribute("route") ClimbingRoute route, Model model) {
            repo.deleteRoute(route.routeID);
            long userID = 2;
            model.addAttribute("routeList", repo.getRoutesByUserID(userID));
            return "gymHomepageRoutes";
        }
        
        @GetMapping("/gym/routes/{id}")
        public String getGymRouteByID(@PathVariable("id") long routeID, Model model) {
            model.addAttribute("route", repo.getRouteById(routeID));
            return "gymHomepageRoute";
        }
        
        @GetMapping("/gym/routes/edit")
        public String editGymRoute(@ModelAttribute("route") ClimbingRoute route, Model model) {
            route = repo.getRouteById(route.routeID);
            model.addAttribute("route", route);
            return "gymHomepageEditRoute";
        }
        
        @PostMapping("gym/routes/edit")
        public String saveEditGymRoute(@ModelAttribute("route") ClimbingRoute route, Model model) {
            repo.editRoute(route.routeID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
            model.addAttribute("route", repo.getRouteById(route.routeID));
            return "gymHomepageRoute";
        }
        
        @GetMapping("/gym/routes/create")
        public String createGymRoute(Model model){
            ClimbingRoute route = new ClimbingRoute();
            model.addAttribute("route", route);
            return "gymHomepageCreateRoute";
        }
        
        @PostMapping("/gym/routes/create")
        public String submitGymRoute(@ModelAttribute("route") ClimbingRoute route, Model model) {
            route.userID = 2;
            repo.addRoute(route.userID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
            model.addAttribute("routeList", repo.getRoutesByUserID(route.userID));
            return "gymHomepageRoutes";
        }
}