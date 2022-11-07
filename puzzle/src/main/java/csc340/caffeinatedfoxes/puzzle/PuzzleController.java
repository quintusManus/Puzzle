
package csc340.caffeinatedfoxes.puzzle;

import csc340.caffeinatedfoxes.puzzle.Route;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PuzzleController {
    
        @Autowired
        private RouteRepository repo;

	@GetMapping("/")
	public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}
        
        @GetMapping("/climber")
        public String climberHomepage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "climberHomepage";
	}
        
        @GetMapping("/climber/routes")
        public String getAllRoutes(Model model) {
            model.addAttribute("routeList", repo.getAllRoutes());
            return "routes";
        }
        
        @GetMapping("/climber/routes/add")
        public String climberHomepageRoutesAdd(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "climberHomepageAddRoute";
	}
        
        @GetMapping("/climber/gyms")
        public String climberHomepageGyms(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "climberHomepageGyms";
	}
}
