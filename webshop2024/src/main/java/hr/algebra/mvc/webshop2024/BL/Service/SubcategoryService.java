package hr.algebra.mvc.webshop2024.BL.Service;

import hr.algebra.mvc.webshop2024.DAL.Entity.Subcategory;

import java.util.List;

public interface SubcategoryService {
    List<Subcategory> findAll();
    Subcategory findById(long id);
    Subcategory save(Subcategory obj);
    void deleteById(long id);
}
