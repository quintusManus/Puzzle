
package csc340.caffeinatedfoxes.puzzle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PuzzleController {

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
        public String climberHomepageRoutes(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "climberHomepageRoutes";
	}
        
        @GetMapping("/climber/gyms")
        public String climberHomepageGyms(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "climberHomepageGyms";
	}
}
