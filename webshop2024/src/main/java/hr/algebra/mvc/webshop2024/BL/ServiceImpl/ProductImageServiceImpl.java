package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.ProductImageService;
import hr.algebra.mvc.webshop2024.DAL.Entity.ProductImage;
import hr.algebra.mvc.webshop2024.DAL.Repository.ProductImageRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepo;

    public ProductImageServiceImpl(ProductImageRepository productImageRepo) {
        this.productImageRepo = productImageRepo;
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageRepo.findAll();
    }

    @Override
    public ProductImage findById(long id) {
        Optional<ProductImage> productImgOptional = productImageRepo.findById(id);

        if (productImgOptional.isEmpty()){
            throw new CustomNotFoundException("Product image id not found - " + id);
        }
        return productImgOptional.get();
    }

    @Override
    public List<ProductImage> findByProduct_ProductId(Long productId) {
        return productImageRepo.findByProduct_ProductId(productId);
    }

    @Override
    @Transactional
    public ProductImage save(ProductImage obj) {
        return productImageRepo.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<ProductImage> checkIfExists = productImageRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Product Image with that ID was not found: " + id);
        }
        productImageRepo.deleteById(id);
    }
}
