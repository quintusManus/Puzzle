package csc340.caffeinatedfoxes.puzzle;
import csc340.caffeinatedfoxes.puzzle.user.User;
import csc340.caffeinatedfoxes.puzzle.user.UserRepository;
import csc340.caffeinatedfoxes.puzzle.api.api;

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
        
    @Autowired
    private GymRouteRepository repo2;
   
     
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
            model.addAttribute("routeList", repo.getAllRoutes());
            return "climberHomepageRoutes";
        }
        
        @PostMapping("/climber/routes")
        public String deleteRouteByID(@ModelAttribute("route") csc340.caffeinatedfoxes.puzzle.Route route, Model model) {
            repo.deleteRoute(route.id);
            model.addAttribute("routeList", repo.getAllRoutes());
            return "climberHomepageRoutes";
        }
        
        @GetMapping("/climber/routes/{id}")
        public String getRouteByID(@PathVariable("id") long id, Model model) {
            csc340.caffeinatedfoxes.puzzle.Route route = repo.getRouteById(id);
            model.addAttribute("route", route);
            repo.addAttemptTable(route.id);
            return "climberHomepageRoute";
        }
        
        @PostMapping("/climber/routes/{id}")
        public String addRouteAttempt(@PathVariable("id") long id, Model model) {
            model.addAttribute("route", repo.getRouteById(id));
            return "climberHomepageRoute";
        }
        
        @GetMapping("/climber/routes/edit")
        public String editRoute(@ModelAttribute("route") csc340.caffeinatedfoxes.puzzle.Route route, Model model) {
            route = repo.getRouteById(route.id);
            model.addAttribute("route", route);
            return "climberHomepageEditRoute";
        }
        
        @PostMapping("climber/routes/edit")
        public String saveEditRoute(@ModelAttribute("route") csc340.caffeinatedfoxes.puzzle.Route route, Model model) {
            repo.editRoute(route.id, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
            model.addAttribute("route", repo.getRouteById(route.id));
            return "climberHomepageRoute";
        }
        
        @GetMapping("/climber/routes/add")
        public String addRoute(Model model){
            csc340.caffeinatedfoxes.puzzle.Route route = new csc340.caffeinatedfoxes.puzzle.Route();
            model.addAttribute("route", route);
            return "climberHomepageAddRoute";
        }
        
        @PostMapping("/climber/routes/add")
        public String submitRoute(@ModelAttribute("route") csc340.caffeinatedfoxes.puzzle.Route route, Model model) {
            repo.addRoute(route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
            model.addAttribute("routeList", repo.getAllRoutes());
            return "climberHomepageRoutes";
        }
        
        @GetMapping("/climber/gyms")
        public String climberHomepageGyms() {
		return "climberHomepageGyms";
	}
        
        @GetMapping("/gym")
        public String gymHomepage(Model model) {
		model.addAttribute("gymrouteList", repo2.getAllRoutes());
		return "gymHomepageRoutes";
	}
        
        @GetMapping("/gym/routes")
        public String getAllGymRoutes(Model model) {
            model.addAttribute("gymrouteList", repo2.getAllRoutes());
            return "gymHomepageRoutes";
        }
        
        @PostMapping("/gym/routes")
        public String deleteGymRouteByID(@ModelAttribute("gymroute") GymRoute gymRoute, Model model) {
            repo2.deleteGymRoute(gymRoute.id);
            model.addAttribute("gymrouteList", repo2.getAllRoutes());
            return "gymHomepageRoutes";
        }
        
        @GetMapping("/gym/routes/{id}")
        public String getGymRouteByID(@PathVariable("id") long id, Model model) {
            model.addAttribute("gymroute", repo2.getRouteById(id));
            return "gymHomepageRoute";
        }
        
        @GetMapping("/gym/routes/edit")
        public String editGymRoute(@ModelAttribute("gymroute") csc340.caffeinatedfoxes.puzzle.GymRoute gymRoute, Model model) {
            gymRoute = repo2.getRouteById(gymRoute.id);
            model.addAttribute("gymroute", gymRoute);
            return "gymHomepageEditRoute";
        }
        
        @PostMapping("gym/routes/edit")
        public String saveEditGymRoute(@ModelAttribute("gymroute") csc340.caffeinatedfoxes.puzzle.GymRoute gymRoute, Model model) {
            repo2.editGymRoute(gymRoute.id, gymRoute.name, gymRoute.difficulty, gymRoute.climbingStyle, gymRoute.locationAndEnvironment, gymRoute.notes);
            model.addAttribute("gymroute", repo2.getRouteById(gymRoute.id));
            return "gymHomepageRoute";
        }
        
        @GetMapping("/gym/routes/create")
        public String createGymRoute(Model model){
            GymRoute gymroute = new GymRoute();
            model.addAttribute("gymroute", gymroute);
            return "gymHomepageCreateRoute";
        }
        
        @PostMapping("/gym/routes/create")
        public String submitGymRoute(@ModelAttribute("gymroute") GymRoute gymroute, Model model) {
            repo2.createRoute(gymroute.name, gymroute.difficulty, gymroute.climbingStyle, gymroute.locationAndEnvironment, gymroute.notes);
            model.addAttribute("gymrouteList", repo2.getAllRoutes());
            return "gymHomepageRoutes";
        }
}