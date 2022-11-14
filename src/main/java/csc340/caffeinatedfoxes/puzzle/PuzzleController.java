package csc340.caffeinatedfoxes.puzzle;
import csc340.caffeinatedfoxes.puzzle.user.User;
import csc340.caffeinatedfoxes.puzzle.user.UserRepository;
import csc340.caffeinatedfoxes.puzzle.api.api;
import csc340.caffeinatedfoxes.puzzle.ClimbingRoute;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PuzzleController {
 
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RouteRepository repo;
    
    @Autowired
    private GymEventRepository repo2;
     
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
    
    @PostMapping("/process_register")
    public String processRegister(User user) {
        userRepo.save(user);
        return "register_success";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/authenticate")
    public String authenticateUser(RedirectAttributes redirectAttributes, @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "password", required = false) String password) {
        boolean value = userRepo.existsByEmail(email);
        boolean value2 = userRepo.existsByPassword(password);
        if(value && value2){
            List<User> listUsers = userRepo.findByEmail(email);
            User user = listUsers.get(0);
            String type = user.getType();
            long id = user.getId();
            redirectAttributes.addAttribute("currentUserId", id);
            if(type.equals("admin")) {
                return "redirect:/users/{currentUserId}";
            }
            else if(type.equals("climber")) {
                return "redirect:/climber/{currentUserId}";
            }
            if(type.equals("gym")){
                return "redirect:/gym/{currentUserId}";
            }
        }
        return "redirect:/login";
    }   
        
        //Climber Route Use Cases
        
        @GetMapping("/climber/routes")
        public String getRoutesByUserID(Model model) {
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
        
    @GetMapping("/users/{currentUserId}")
	public String listUsers(Model model, @PathVariable("currentUserId") long currentUserId) {
        System.out.println(currentUserId);
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
        
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }


    @GetMapping("/climber/{currentUserId}")
    public String climberHomepage(Model model, @PathVariable("currentUserId") long currentUserId) {
            return "climberHomepage";
    }

    @GetMapping("/gym/{currentUserId}")
    public String gymHomepage(Model model, @PathVariable("currentUserId") long currentUserId) {
            model.addAttribute("routeList", repo.getRoutesByUserID(currentUserId));
            return "gymHomepageRoutes";
    }

    


    //Gym Actor
    
    @GetMapping("/gym/events")
    public String getAllGymEvents(Model model) {
        model.addAttribute("gymeventList", repo2.getAllEvents());
        return "gymHomepageEvents";
    }

    @PostMapping("/gym/events")
    public String deleteGymEventByID(@ModelAttribute("gymevent") GymEvent gymEvent, Model model) {
        repo2.deleteGymEvent(gymEvent.id);
        model.addAttribute("gymeventList", repo2.getAllEvents());
        return "gymHomepageEvents";
    }
    
    @GetMapping("/gym/events/create")
    public String createGymEvent(Model model){
        GymEvent gymevent = new GymEvent();
        model.addAttribute("gymevent", gymevent);
        return "gymHomepageCreateEvent";
    }

    @PostMapping("/gym/events/create")
    public String submitGymEvent(@ModelAttribute("gymevent") GymEvent gymevent, Model model) {
        repo2.createEvent(gymevent.name, gymevent.description);
        model.addAttribute("gymeventList", repo2.getAllEvents());
        return "gymHomepageEvents";
    }
    
    @GetMapping("/gym/events/{id}")
    public String getGymEventByID(@PathVariable("id") long id, Model model) {
        model.addAttribute("gymevent", repo2.getEventById(id));
        return "gymHomepageEvent";
    }
}