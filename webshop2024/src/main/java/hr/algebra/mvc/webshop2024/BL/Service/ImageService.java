package hr.algebra.mvc.webshop2024.BL.Service;


import hr.algebra.mvc.webshop2024.DAL.Entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> findAll();
    Image findById(long id);
    Image save(Image obj);
    void deleteById(long id);
}
