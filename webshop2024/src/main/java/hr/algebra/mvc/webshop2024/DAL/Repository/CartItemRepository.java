package hr.algebra.mvc.webshop2024.DAL.Repository;

import hr.algebra.mvc.webshop2024.DAL.Entity.CartItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;
import hr.algebra.mvc.webshop2024.DAL.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
