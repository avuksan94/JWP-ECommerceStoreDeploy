package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.AuthorityService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Authority;
import hr.algebra.mvc.webshop2024.DAL.Repository.AuthorityRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authRepo;

    public AuthorityServiceImpl(AuthorityRepository authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public List<Authority> findAll() {
        return authRepo.findAll();
    }

    @Override
    public Authority findById(long id) {
        Optional<Authority> authorityOptional = authRepo.findById(id);

        if (authorityOptional.isEmpty()){
            throw new CustomNotFoundException("Authority id not found - " + id);
        }

        return authorityOptional.get();
    }

    @Override
    @Transactional
    public Authority save(Authority authority) {
        return authRepo.save(authority);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Authority> checkIfExists = authRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Authority with that ID was not found: " + id);
        }
        authRepo.deleteById(id);
    }

}

