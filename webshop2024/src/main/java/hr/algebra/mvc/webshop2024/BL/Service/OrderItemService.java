package hr.algebra.mvc.webshop2024.BL.Service;

import hr.algebra.mvc.webshop2024.DAL.Entity.Order;
import hr.algebra.mvc.webshop2024.DAL.Entity.OrderItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> findAll();
    OrderItem findById(long id);
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
    List<OrderItem> findByOrder(Order order);
    OrderItem save(OrderItem obj);
    void deleteById(long id);
}
