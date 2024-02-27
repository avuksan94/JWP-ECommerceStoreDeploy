package hr.algebra.mvc.webshop2024.BL.Service;


import hr.algebra.mvc.webshop2024.DAL.Entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> findAll();
    ProductImage findById(long id);
    List<ProductImage> findByProduct_ProductId(Long productId);
    ProductImage save(ProductImage obj);
    void deleteById(long id);
}
