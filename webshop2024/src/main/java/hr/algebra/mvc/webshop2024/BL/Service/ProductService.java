package hr.algebra.mvc.webshop2024.BL.Service;

import hr.algebra.mvc.webshop2024.DAL.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(long id);
    Product save(Product obj);
    void deleteById(long id);
    List<Product> findByNameLike(String keyword);
    List<Product> findByKeyword(String keyword);
    Product getProductById(long productId);
}
