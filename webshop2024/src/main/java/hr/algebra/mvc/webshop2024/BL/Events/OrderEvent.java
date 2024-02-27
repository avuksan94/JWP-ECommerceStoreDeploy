package hr.algebra.mvc.webshop2024.BL.Events;

import hr.algebra.mvc.webshop2024.DAL.Entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderEvent  extends ApplicationEvent {
    private final Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
}
