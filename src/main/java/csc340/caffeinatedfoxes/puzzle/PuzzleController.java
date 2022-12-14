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

/**
 * The main controller class.
 * @author smuska, sdbridges, bjwoods
 * Last Updated: 11/15/2022
 */
@Controller
public class PuzzleController {
 
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RouteRepository repo;
    
    @Autowired
    private GymEventRepository repo2;
     
    //Mapping for login and registration
    
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
    
     @GetMapping("/users/{currentUserId}")
	public String listUsers(Model model, @PathVariable("currentUserId") long currentUserId) {
        System.out.println(currentUserId);
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}


    @GetMapping("/deleteUser/{currentUserId}/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id, @PathVariable("currentUserId") long currentUserId, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        userRepo.deleteById(id);
        return "redirect:/users/{currentUserId}";
    }


    @GetMapping("/climber/{currentUserId}")
    public String climberHomepage(Model model, @PathVariable("currentUserId") long currentUserId) {
            model.addAttribute("currentUserId", currentUserId);
            model.addAttribute("routeList", repo.getRoutesByUserID(currentUserId));
            return "climberHomepageRoutes";
    }

    @GetMapping("/gym/{currentUserId}")
    public String gymHomepage(Model model, @PathVariable("currentUserId") long currentUserId) {
            model.addAttribute("routeList", repo.getRoutesByUserID(currentUserId));
            return "gymHomepageRoutes";
    }
        
    //Use cases related to the ClimbingRoute Object.
        
    @GetMapping("/climber/{currentUserId}/routes")
    public String getRoutesByUserID(@PathVariable("currentUserId") long currentUserId, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        model.addAttribute("routeList", repo.getRoutesByUserID(currentUserId));
        return "climberHomepageRoutes";
    }   
        
    @PostMapping("/climber/{currentUserId}/routes")
    public String deleteRouteByID(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        repo.deleteRoute(route.routeID);
        model.addAttribute("routeList", repo.getRoutesByUserID(currentUserId));
        return "climberHomepageRoutes";
    }
        
    @GetMapping("/climber/{currentUserId}/routes/{id}")
    public String getRouteByID(@PathVariable("currentUserId") long currentUserId, @PathVariable("id") long routeID, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        ClimbingRoute route = repo.getRouteById(routeID);
        model.addAttribute("route", route);
        Attempt attempt = new Attempt();
        model.addAttribute("attempt", attempt);
        model.addAttribute("attemptList", repo.getAllRouteAttempts(routeID));
        return "climberHomepageRoute";
    }
        
    @PostMapping("/climber/{currentUserId}/routes/{id}")
    public String addRouteAttempt(@PathVariable("currentUserId") long currentUserId, @PathVariable("id") long routeID, @ModelAttribute("attempt") Attempt attempt, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        repo.addRouteAttempt(routeID, attempt.date, attempt.numOfFalls);
        ClimbingRoute route = repo.getRouteById(routeID);
        model.addAttribute("route", route);
        model.addAttribute("attemptList", repo.getAllRouteAttempts(routeID));
        return "climberHomepageRoute";
    }
        
    @GetMapping("/climber/{currentUserId}/routes/edit")
    public String editRoute(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        route = repo.getRouteById(route.routeID);
        model.addAttribute("route", route);
        return "climberHomepageEditRoute";
    }
        
    @PostMapping("climber/{currentUserId}/routes/edit")
    public String saveEditRoute(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        repo.editRoute(route.routeID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
        model.addAttribute("route", repo.getRouteById(route.routeID));
        long routeID = route.routeID;
        Attempt attempt = new Attempt();
        model.addAttribute("attempt", attempt);
        model.addAttribute("attemptList", repo.getAllRouteAttempts(routeID));
        return "climberHomepageRoute";
    }
        
    @GetMapping("/climber/{currentUserId}/routes/add")
    public String addRoute(@PathVariable("currentUserId") long currentUserId, Model model){
        model.addAttribute("currentUserId", currentUserId);
        ClimbingRoute route = new ClimbingRoute();
        model.addAttribute("route", route);
        return "climberHomepageAddRoute";
    }
        
    @PostMapping("/climber/{currentUserId}/routes/add")
    public String submitRoute(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        route.userID = currentUserId;
        repo.addRoute(route.userID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
        model.addAttribute("routeList", repo.getRoutesByUserID(route.userID));
        return "climberHomepageRoutes";
    }
        
    @GetMapping("/gym/{currentUserId}/routes")
    public String getAllGymRoutes(@PathVariable("currentUserId") long currentUserId, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        long userID = currentUserId;
        model.addAttribute("routeList", repo.getRoutesByUserID(userID));
        return "gymHomepageRoutes";
    }
        
    @PostMapping("/gym/{currentUserId}/routes")
    public String deleteGymRouteByID(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        repo.deleteRoute(route.routeID);
        long userID = currentUserId;
        model.addAttribute("routeList", repo.getRoutesByUserID(userID));
        return "gymHomepageRoutes";
    }
        
    @GetMapping("/gym/{currentUserId}/routes/{id}")
    public String getGymRouteByID(@ModelAttribute("currentUserId") long currentUserId, @PathVariable("id") long routeID, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        model.addAttribute("route", repo.getRouteById(routeID));
        return "gymHomepageRoute";
    }
        
    @GetMapping("/gym/{currentUserId}/routes/edit")
    public String editGymRoute(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        route = repo.getRouteById(route.routeID);
        model.addAttribute("route", route);
        return "gymHomepageEditRoute";
    }
        
    @PostMapping("gym/{currentUserId}/routes/edit")
    public String saveEditGymRoute(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        repo.editRoute(route.routeID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
        model.addAttribute("route", repo.getRouteById(route.routeID));
        return "gymHomepageRoute";
    }
        
    @GetMapping("/gym/{currentUserId}/routes/create")
    public String createGymRoute(@PathVariable("currentUserId") long currentUserId, Model model){
        model.addAttribute("currentUserId", currentUserId);
        ClimbingRoute route = new ClimbingRoute();
        model.addAttribute("route", route);
        return "gymHomepageCreateRoute";
    }
        
    @PostMapping("/gym/{currentUserId}/routes/create")
    public String submitGymRoute(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("route") ClimbingRoute route, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        route.userID = currentUserId;
        repo.addRoute(route.userID, route.name, route.difficulty, route.climbingStyle, route.locationAndEnvironment, route.notes);
        model.addAttribute("routeList", repo.getRoutesByUserID(route.userID));
        return "gymHomepageRoutes";
    }
    
    @GetMapping("/climber/{currentUserId}/gyms")
    public String getGyms(@PathVariable("currentUserId") long currentUserId, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        model.addAttribute("routeList", repo.getRoutesByUserID(currentUserId));
        model.addAttribute("filterGyms", userRepo.search(keyword));       
        return "climberHomepageGyms";
    }

    //Use-Cases related to the GymEvent object.
    
    @GetMapping("/gym/{currentUserId}/events")
    public String getAllGymEvents(@PathVariable("currentUserId") long currentUserId, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        long userID = currentUserId;
        model.addAttribute("gymeventList", repo2.getAllEventsByUserID(userID));
        return "gymHomepageEvents";
    }

    @PostMapping("/gym/{currentUserId}/events")
    public String deleteGymEventByID(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("gymevent") GymEvent gymEvent, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        repo2.deleteGymEvent(gymEvent.eventID);
        long userID = currentUserId;
        model.addAttribute("gymeventList", repo2.getAllEventsByUserID(userID));
        return "gymHomepageEvents";
    }
    
    @GetMapping("/gym/{currentUserId}/events/create")
    public String createGymEvent(@PathVariable("currentUserId") long currentUserId, Model model){
        model.addAttribute("currentUserId", currentUserId);
        GymEvent gymevent = new GymEvent();
        model.addAttribute("gymevent", gymevent);
        return "gymHomepageCreateEvent";
    }

    @PostMapping("/gym/{currentUserId}/events/create")
    public String submitGymEvent(@PathVariable("currentUserId") long currentUserId, @ModelAttribute("gymevent") GymEvent gymevent, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        gymevent.userID = currentUserId;
        repo2.createEvent(gymevent.userID, gymevent.title, gymevent.description);
        model.addAttribute("gymeventList", repo2.getAllEventsByUserID(currentUserId));
        return "gymHomepageEvents";
    }
    
    @GetMapping("/gym/{currentUserId}/events/{id}")
    public String getGymEventByID(@PathVariable("currentUserId") long currentUserId, @PathVariable("id") long id, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        model.addAttribute("gymevent", repo2.getEventById(id));
        return "gymHomepageEvent";
    }
}