package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("greeting")
    public ModelAndView getView() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
