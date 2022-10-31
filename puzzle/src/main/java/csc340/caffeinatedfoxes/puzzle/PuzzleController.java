
package csc340.caffeinatedfoxes.puzzle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sofie
 */

//The @Controller annotation is a specialization of the generic stereotype @Component annotation, which allows a class to be recognized as a Spring-managed component.
@Controller
public class PuzzleController {
        // handler method to handle home page request
    @GetMapping("/")
    public String showHome(Model model){
        return "index";
    }
}
}
