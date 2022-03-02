package code.service;

import code.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAllCategories();

    Optional<Category> findById(Long id);
}
