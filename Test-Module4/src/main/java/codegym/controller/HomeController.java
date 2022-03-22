package codegym.controller;

import codegym.model.City;
import codegym.model.Country;
import codegym.service.ICityService;
import codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ModelAndView showCities() {
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<City> cities = cityService.FindAllCities();
        if (!cities.iterator().hasNext()) {
            modelAndView.addObject("message", "We don't have any city");
        }
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Country> countries = countryService.findAllCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView sava(@ModelAttribute City city) {
        ModelAndView modelAndView = new ModelAndView("create");
        City cityCreate = cityService.save(city);
        if (cityCreate != null) {
            Iterable<Country> countries = countryService.findAllCountries();
            modelAndView.addObject("countries", countries);
            modelAndView.addObject("message", "Create City Successfully !!!");
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        cityService.remove(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<City> city = cityService.findById(id);
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<City> city = cityService.findById(id);
        Iterable<Country> countries = countryService.findAllCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute City city) {
        ModelAndView modelAndView = new ModelAndView("edit");
        city.setId(id);
        City cityEdit = cityService.save(city);
        if (cityEdit != null) {
            modelAndView.addObject("countries", countryService.findAllCountries());
            modelAndView.addObject("message", "Update City Successfully");
        }
        return modelAndView;
    }
}
