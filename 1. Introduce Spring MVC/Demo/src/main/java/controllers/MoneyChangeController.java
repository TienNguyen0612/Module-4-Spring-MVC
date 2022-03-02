package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoneyChangeController {
    @GetMapping("change")
    public String getView() {
        return "change";
    }

    @PostMapping("change")
    public ModelAndView result(@RequestParam("usd") String usd, @RequestParam("rate") String rate) {
        ModelAndView modelAndView = new ModelAndView("change-money");
        double result = Double.parseDouble(usd) * Double.parseDouble(rate);
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
