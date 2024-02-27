package hr.algebra.mvc.webshop2024.BL.Events;

import hr.algebra.mvc.webshop2024.DAL.Entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

//https://medium.com/hprog99/mastering-events-in-spring-boot-a-comprehensive-guide-86348f968fc6

@Getter
public class UserRegistrationEvent extends ApplicationEvent {
    private final User user;

    public UserRegistrationEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
