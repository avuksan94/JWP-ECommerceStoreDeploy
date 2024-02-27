package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.ProductService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;
import hr.algebra.mvc.webshop2024.DAL.Repository.ProductRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(long id) {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isEmpty()){
            throw new CustomNotFoundException("Product id not found - " + id);
        }
        return productOptional.get();
    }

    @Override
    @Transactional
    public Product save(Product obj) {
        return productRepo.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Product> checkIfExists = productRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Product with that ID was not found: " + id);
        }
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> findByNameLike(String keyword) {
        return productRepo.findByNameLike(keyword);
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        return productRepo.findByKeyword(keyword);
    }

    @Override
    public Product getProductById(long productId) {
        return productRepo.getProductByProductId(productId);
    }
}
