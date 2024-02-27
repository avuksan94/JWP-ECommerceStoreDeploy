package hr.algebra.mvc.webshop2024.BL.Events;

import hr.algebra.mvc.webshop2024.DAL.Entity.Notification;
import hr.algebra.mvc.webshop2024.DAL.Entity.User;
import hr.algebra.mvc.webshop2024.DAL.Repository.NotificationRepository;
import hr.algebra.mvc.webshop2024.DAL.Repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserRegistrationListener implements ApplicationListener<UserRegistrationEvent> {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public UserRegistrationListener(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        User getAdminUser = userRepository.findByUsername("admin");
        notificationRepository.save(
                new Notification(
                        getAdminUser,
                        "New account creation",
                        "New account has been created!",
                        LocalDateTime.now(),
                        null,
                        false
                )
        );
    }
}
