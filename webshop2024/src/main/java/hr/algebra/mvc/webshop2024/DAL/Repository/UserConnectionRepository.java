package hr.algebra.mvc.webshop2024.DAL.Repository;

import hr.algebra.mvc.webshop2024.DAL.Entity.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection,Long> {
}
