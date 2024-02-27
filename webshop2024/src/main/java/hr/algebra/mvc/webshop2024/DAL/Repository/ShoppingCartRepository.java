package hr.algebra.mvc.webshop2024.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.algebra.mvc.webshop2024.DAL.Entity.ShoppingCart;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findBySessionId(String sessionId);
    Optional<ShoppingCart> findByUsername(String username);
}
