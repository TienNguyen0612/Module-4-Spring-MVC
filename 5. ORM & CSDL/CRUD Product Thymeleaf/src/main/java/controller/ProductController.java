package controller;

import model.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import regex.Validate;
import service.IProductService;
import service.impl.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService = new ProductService();
    private final Validate validate = new Validate();

    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView save(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        List<Product> products = productService.getAll();
        boolean check = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                productService.update(product.getId(), product);
                check = true;
            }
        }
        if (!check) {
            product.setId((int) (Math.random() * 1000));
            productService.save(product);
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editProduct(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        productService.remove(id);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView showDetail(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchByName(@RequestParam("search") String search) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productService.getAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            if (validate.validateNameProduct(search, product.getName())) {
                productList.add(product);
            }
        }
        modelAndView.addObject("products", productList);
        return modelAndView;
    }
}
