package hr.algebra.mvc.webshop2024.BL.Service;

import hr.algebra.mvc.webshop2024.DAL.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);
    void deleteByUsername(String username);
    List<User> getByKeyword(String keyword);
    void createAdminUser(String username, String rawPassword,String email);
    void createShopperUser(String username, String rawPassword,String email);
}
