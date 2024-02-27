package hr.algebra.mvc.webshop2024.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.algebra.mvc.webshop2024.DAL.Entity.Subcategory;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
}
