package hr.algebra.mvc.webshop2024.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.algebra.mvc.webshop2024.DAL.Entity.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByUsername(String username);
    List<Order> findAllByUsername(String username);
    List<Order> findByUsernameAndPurchaseDateBetween(String username, LocalDateTime startDate, LocalDateTime endDate);
}