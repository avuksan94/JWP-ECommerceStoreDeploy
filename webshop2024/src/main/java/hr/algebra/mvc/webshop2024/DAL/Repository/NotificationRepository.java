package hr.algebra.mvc.webshop2024.DAL.Repository;

import hr.algebra.mvc.webshop2024.DAL.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
