package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.SubcategoryService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Subcategory;
import hr.algebra.mvc.webshop2024.DAL.Repository.SubcategoryRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepo;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepo) {
        this.subcategoryRepo = subcategoryRepo;
    }

    @Override
    public List<Subcategory> findAll() {
        return subcategoryRepo.findAll();
    }

    @Override
    public Subcategory findById(long id) {
        Optional<Subcategory> subcatOptional = subcategoryRepo.findById(id);

        if (subcatOptional.isEmpty()){
            throw new CustomNotFoundException("Subcategory id not found - " + id);
        }
        return subcatOptional.get();
    }

    @Override
    @Transactional
    public Subcategory save(Subcategory obj) {
        return subcategoryRepo.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Subcategory> checkIfExists = subcategoryRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Subcategory with that ID was not found: " + id);
        }
        subcategoryRepo.deleteById(id);
    }
}
