package hr.algebra.mvc.webshop2024.BL.Service;

import hr.algebra.mvc.webshop2024.DAL.Entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(long id);
    Category save(Category obj);
    void deleteById(long id);
}
