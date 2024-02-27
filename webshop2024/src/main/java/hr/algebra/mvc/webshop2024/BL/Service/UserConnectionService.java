package hr.algebra.mvc.webshop2024.BL.Service;


import hr.algebra.mvc.webshop2024.DAL.Entity.UserConnection;

import java.util.List;

public interface UserConnectionService {
    List<UserConnection> findAll();
    UserConnection findById(long id);
    UserConnection save(UserConnection obj);
    void deleteById(long id);
}
