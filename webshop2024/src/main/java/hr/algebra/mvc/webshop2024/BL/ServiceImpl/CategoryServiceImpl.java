package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.CategoryService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Category;
import hr.algebra.mvc.webshop2024.DAL.Repository.CategoryRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(long id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);

        if (categoryOptional.isEmpty()){
            throw new CustomNotFoundException("Category id not found - " + id);
        }

        return categoryOptional.get();
    }

    @Override
    @Transactional
    public Category save(Category obj) {
        return categoryRepo.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Category> checkIfExists = categoryRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Category with that ID was not found: " + id);
        }
        categoryRepo.deleteById(id);
    }
}
