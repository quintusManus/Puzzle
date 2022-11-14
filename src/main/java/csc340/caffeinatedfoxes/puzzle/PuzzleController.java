package csc340.caffeinatedfoxes.puzzle;
import csc340.caffeinatedfoxes.puzzle.user.User;
import csc340.caffeinatedfoxes.puzzle.user.UserRepository;
import csc340.caffeinatedfoxes.puzzle.api.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PuzzleController {
 
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RouteRepository repo;
        
    @Autowired
    private GymRouteRepository repo2;
    
    @Autowired
    private GymEventRepository repo3;
   
     
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
    public String authenticateUser(@RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "password", required = false) String password) {
        boolean value = userRepo.existsByEmail(email);
        boolean value2 = userRepo.existsByPassword(password);
        if(value && value2){
            List<User> listUsers = userRepo.findByEmail(email);
            User user = listUsers.get(0);
            String type = user.getType();
            if(type.equals("admin")) {
                return "redirect:/users";
            }
            else if(type.equals("climber")) {
                return "redirect:/climber";
            }
            if(type.equals("gym")){
                return "redirect:/gym";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
            List<User> listUsers = userRepo.findAll();
            model.addAttribute("listUsers", listUsers);
            return "users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
    userRepo.deleteById(id);
    return "redirect:/users";
    }

    //Climber actor
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
        model.addAttribute("route", repo.getRouteById(id));
        return "climberHomepageRoute";
    }

    @GetMapping("/climber/routes/{id}/edit")
    public String editRoute(@PathVariable("id") long id, Model model) {
        model.addAttribute("route", repo.getRouteById(id));
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

    //Gym Actor
    @GetMapping("/gym")
    public String gymHomepage(Model model) {
            model.addAttribute("gymrouteList", repo2.getAllRoutes());
            return "gymHomepage";
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
    
    @GetMapping("/gym/events")
    public String getAllGymEvents(Model model) {
        model.addAttribute("gymeventList", repo3.getAllEvents());
        return "gymHomepageEvents";
    }

    @PostMapping("/gym/events")
    public String deleteGymEventByID(@ModelAttribute("gymevent") GymEvent gymEvent, Model model) {
        repo3.deleteGymEvent(gymEvent.id);
        model.addAttribute("gymeventList", repo3.getAllEvents());
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
        repo3.createEvent(gymevent.name, gymevent.description);
        model.addAttribute("gymeventList", repo3.getAllEvents());
        return "gymHomepageEvents";
    }
    
    @GetMapping("/gym/events/{id}")
    public String getGymEventByID(@PathVariable("id") long id, Model model) {
        model.addAttribute("gymevent", repo3.getEventById(id));
        return "gymHomepageEvent";
    }
}