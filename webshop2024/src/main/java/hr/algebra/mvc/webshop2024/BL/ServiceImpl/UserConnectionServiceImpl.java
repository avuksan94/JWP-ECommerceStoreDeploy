package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.UserConnectionService;
import hr.algebra.mvc.webshop2024.DAL.Entity.UserConnection;
import hr.algebra.mvc.webshop2024.DAL.Repository.UserConnectionRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserConnectionServiceImpl implements UserConnectionService {
    private final UserConnectionRepository userConnectionRepository;

    public UserConnectionServiceImpl(UserConnectionRepository userConnectionRepository) {
        this.userConnectionRepository = userConnectionRepository;
    }

    @Override
    public List<UserConnection> findAll() {
        return userConnectionRepository.findAll();
    }

    @Override
    public UserConnection findById(long id) {
        Optional<UserConnection> userConnectionOptional = userConnectionRepository.findById(id);

        if (userConnectionOptional.isEmpty()){
            throw new CustomNotFoundException("Connection id not found - " + id);
        }

        return userConnectionOptional.get();
    }

    @Override
    @Transactional
    public UserConnection save(UserConnection obj) {
        return  userConnectionRepository.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<UserConnection> checkIfExists = userConnectionRepository.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Connection with that ID was not found: " + id);
        }
        userConnectionRepository.deleteById(id);
    }
}
