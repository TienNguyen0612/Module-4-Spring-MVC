package codegym.controller;

import codegym.model.Human;
import codegym.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/human")
public class HumanController {
    @Autowired
    private IHumanService humanService;

    @GetMapping
    public ModelAndView showAll(@RequestParam("search") Optional<String> search, @PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Human> humans;
        if (search.isPresent()) {
            humans = humanService.findByNameContaining(pageable, search.get());
            modelAndView.addObject("search", search.get());
        } else {
            humans = humanService.findAll(pageable);
        }
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }

    @GetMapping("/create-human")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("human", new Human());
        return modelAndView;
    }

    @PostMapping("/save-human")
    public ModelAndView save(@Valid @ModelAttribute Human human, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message", "Create failed");
            modelAndView.addObject("color", "Red");
            modelAndView.addObject("human", human);
        } else {
            modelAndView.addObject("message", "Create Successful");
            modelAndView.addObject("color", "Blue");
            modelAndView.addObject("student", new Human());
            humanService.save(human);
        }
        return modelAndView;
    }

    @GetMapping("/delete-human/{id}")
    public ModelAndView deleteHuman(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/human");
        Optional<Human> human = humanService.findById(id);
        if (human.isPresent()) {
            humanService.delete(id);
        }
        return modelAndView;
    }

    @GetMapping("/edit-human/{id}")
    public ModelAndView editHuman(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<Human> human = humanService.findById(id);
        modelAndView.addObject("human", human);
        return modelAndView;
    }

    @PostMapping("/edit-human/{id}")
    public ModelAndView updateHuman(@Valid @ModelAttribute("human") Human human, BindingResult bindingResult, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("message", "Update failed");
            modelAndView.addObject("color", "Red");
            modelAndView.addObject("human", human);
            return modelAndView;
        }
        human.setId(id);
        Human humanEdit = humanService.save(human);
        if (humanEdit != null) {
            modelAndView.addObject("message", "Update Successful");
            modelAndView.addObject("color", "Blue");
        }
        return modelAndView;
    }

    @GetMapping("/sort-asc")
    public ModelAndView sortAsc(@SortDefault (sort = "name") @PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Human> humans = humanService.findAll(pageable);
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }

    @GetMapping("/sort-desc")
    public ModelAndView sortDesc(@SortDefault (sort = "name", direction = Sort.Direction.DESC) @PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Human> humans = humanService.findAll(pageable);
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }
}
