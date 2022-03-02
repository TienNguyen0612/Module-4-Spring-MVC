package service.impl;

import model.Product;
import service.IProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "iPhone X", 100, "Like New", "Apple"));
        products.put(2, new Product(2, "Galaxy S9", 50, "New", "Samsung"));
        products.put(3, new Product(3, "Edra384", 300, "99,99%", "China"));
        products.put(4, new Product(4, "Dell Vostro 12", 500, "Like New", "Dell"));
        products.put(5, new Product(5, "iPhone 13 Pro", 1000, "New", "Apple"));
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product getProduct(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
