package hr.algebra.mvc.webshop2024.DAL.Repository;

import hr.algebra.mvc.webshop2024.DAL.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
