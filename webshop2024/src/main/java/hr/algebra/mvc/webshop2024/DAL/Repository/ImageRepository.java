package hr.algebra.mvc.webshop2024.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.algebra.mvc.webshop2024.DAL.Entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
