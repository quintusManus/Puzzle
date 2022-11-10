
package csc340.caffeinatedfoxes.puzzle;

import csc340.caffeinatedfoxes.puzzle.Route;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smuska
 * The main application controller class.
 * Last Updated: 11/8/2022
 */
@Controller
public class PuzzleController {
    
        @Autowired
        private RouteRepository repo;
        
        @Autowired
        private GymRouteRepository repo2;

	@GetMapping("/")
	public String index() {
            return "index";
	}
        
        @GetMapping("/climber")
        public String climberHomepage() {
            return "climberHomepage";
	}
        
        @GetMapping("/climber/routes")
        public String getAllRoutes(Model model) {
            model.addAttribute("routeList", repo.getAllRoutes());
            return "climberHomepageRoutes";
        }
        
        @PostMapping("/climber/routes")
        public String deleteRouteByID(@ModelAttribute("route") Route route, Model model) {
            repo.deleteRoute(route.id);
            model.addAttribute("routeList", repo.getAllRoutes());
            return "climberHomepageRoutes";
        }
        
        @GetMapping("/climber/routes/{id}")
        public String getRouteByID(@PathVariable("id") long id, Model model) {
            model.addAttribute("route", repo.getRouteById(id));
            return "climberHomepageRoute";
        }
        
        @GetMapping("/climber/routes/add")
        public String addRoute(Model model){
            Route route = new Route();
            model.addAttribute("route", route);
            return "climberHomepageAddRoute";
        }
        
        @PostMapping("/climber/routes/add")
        public String submitRoute(@ModelAttribute("route") Route route, Model model) {
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
