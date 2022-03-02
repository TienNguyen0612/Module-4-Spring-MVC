package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    void save(Product product);

    Product getProduct(int id);

    void update(int id, Product product);

    void remove(int id);
}
