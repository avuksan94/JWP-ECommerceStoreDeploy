package hr.algebra.mvc.webshop2024.BL.Events;


import hr.algebra.mvc.webshop2024.DAL.Entity.Notification;
import hr.algebra.mvc.webshop2024.DAL.Entity.User;
import hr.algebra.mvc.webshop2024.DAL.Repository.NotificationRepository;
import hr.algebra.mvc.webshop2024.DAL.Repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderListener implements ApplicationListener<OrderEvent> {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public OrderListener(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(OrderEvent event) {
        User getAdminUser = userRepository.findByUsername("admin");
        notificationRepository.save(
                new Notification(
                        getAdminUser,
                        "New order made",
                        "New order was made!",
                        LocalDateTime.now(),
                        null,
                        false
                )
        );
    }
}
