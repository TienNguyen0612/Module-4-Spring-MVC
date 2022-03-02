package code.controllers;

import code.model.Category;
import code.model.Song;
import code.service.ICategoryService;
import code.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISongService songService;

    @GetMapping
    public ModelAndView showCategories() {
        ModelAndView modelAndView = new ModelAndView("category/list");
        Iterable<Category> categories = categoryService.findAllCategories();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("category/view");
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ModelAndView("error.404");
        }
        Iterable<Song> songs = songService.findAllByCategory(categoryOptional.get());
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }
}
