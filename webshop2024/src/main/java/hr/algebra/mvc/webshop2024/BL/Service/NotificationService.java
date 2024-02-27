package hr.algebra.mvc.webshop2024.BL.Service;


import hr.algebra.mvc.webshop2024.DAL.Entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> findAll();
    Notification findById(long id);
    Notification save(Notification obj);
    void deleteById(long id);
}
