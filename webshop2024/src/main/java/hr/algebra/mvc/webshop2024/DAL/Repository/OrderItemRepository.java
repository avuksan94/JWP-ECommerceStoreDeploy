package hr.algebra.mvc.webshop2024.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.algebra.mvc.webshop2024.DAL.Entity.Order;
import hr.algebra.mvc.webshop2024.DAL.Entity.OrderItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
    List<OrderItem> findByOrder(Order order);
}
