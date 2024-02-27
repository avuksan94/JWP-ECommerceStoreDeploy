package hr.algebra.mvc.webshop2024.BL.ServiceImpl;


import hr.algebra.mvc.webshop2024.BL.Service.ImageService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Image;
import hr.algebra.mvc.webshop2024.DAL.Repository.ImageRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepo;

    public ImageServiceImpl(ImageRepository imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public List<Image> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public Image findById(long id) {
        Optional<Image> imageOptional = imageRepo.findById(id);

        if (imageOptional.isEmpty()){
            throw new CustomNotFoundException("Image id not found - " + id);
        }
        return imageOptional.get();
    }

    @Override
    @Transactional
    public Image save(Image obj) {
        return imageRepo.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Image> checkIfExists = imageRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Image with that ID was not found: " + id);
        }
        imageRepo.deleteById(id);
    }
}
