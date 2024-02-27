package hr.algebra.mvc.webshop2024.BL.ServiceImpl;


import hr.algebra.mvc.webshop2024.BL.Service.NotificationService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Notification;
import hr.algebra.mvc.webshop2024.DAL.Repository.NotificationRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findById(long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);

        if (notificationOptional.isEmpty()){
            throw new CustomNotFoundException("Notification id not found - " + id);
        }

        return notificationOptional.get();
    }

    @Override
    public Notification save(Notification obj) {
        return notificationRepository.save(obj);
    }

    @Override
    public void deleteById(long id) {
        Optional<Notification> checkIfExists = notificationRepository.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Notification with that ID was not found: " + id);
        }
        notificationRepository.deleteById(id);
    }
}
