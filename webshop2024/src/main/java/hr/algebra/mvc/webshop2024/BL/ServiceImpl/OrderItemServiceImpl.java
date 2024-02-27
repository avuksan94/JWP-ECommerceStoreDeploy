package hr.algebra.mvc.webshop2024.BL.ServiceImpl;


import hr.algebra.mvc.webshop2024.BL.Service.OrderItemService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Order;
import hr.algebra.mvc.webshop2024.DAL.Entity.OrderItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;
import hr.algebra.mvc.webshop2024.DAL.Repository.OrderItemRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepo;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepo.findAll();
    }

    @Override
    public OrderItem findById(long id) {
        Optional<OrderItem> orderItemOptional = orderItemRepo.findById(id);

        if (orderItemOptional.isEmpty()){
            throw new CustomNotFoundException("Order Item id not found - " + id);
        }
        return orderItemOptional.get();
    }

    @Override
    public Optional<OrderItem> findByOrderAndProduct(Order order, Product product) {
        return orderItemRepo.findByOrderAndProduct(order,product);
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepo.findByOrder(order);
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem obj) {
        return orderItemRepo.save(obj);
    }

    @Override
    public void deleteById(long id) {
        Optional<OrderItem> checkIfExists = orderItemRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Order Item with that ID was not found: " + id);
        }
        orderItemRepo.deleteById(id);
    }
}
