package hr.algebra.mvc.webshop2024.BL.Service;

import hr.algebra.mvc.webshop2024.DAL.Entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAll();
    Authority findById(long id);
    Authority save(Authority authority);
    void deleteById(long id);
}
