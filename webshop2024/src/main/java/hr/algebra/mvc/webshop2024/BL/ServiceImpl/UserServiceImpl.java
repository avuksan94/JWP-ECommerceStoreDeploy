package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Events.UserRegistrationEvent;
import hr.algebra.mvc.webshop2024.BL.Service.SecurityService;
import hr.algebra.mvc.webshop2024.BL.Service.UserService;
import hr.algebra.mvc.webshop2024.DAL.Entity.Authority;
import hr.algebra.mvc.webshop2024.DAL.Entity.User;
import hr.algebra.mvc.webshop2024.DAL.Enum.Role;
import hr.algebra.mvc.webshop2024.DAL.Repository.AuthorityRepository;
import hr.algebra.mvc.webshop2024.DAL.Repository.UserRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {
    private final UserRepository userRepo;
    private final AuthorityRepository authorityRepo;
    private final SecurityService securityService;
    private ApplicationContext applicationContext;

    public UserServiceImpl(UserRepository userRepo, AuthorityRepository authorityRepo, SecurityService securityService) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
        this.securityService = securityService;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByUsername(username));

        if (userOptional.isEmpty()){
            throw new CustomNotFoundException("User with that username not found - " + username);
        }

        return userOptional.get();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(email));

        if (userOptional.isEmpty()){
            throw new CustomNotFoundException("User with that email not found - " + email);
        }

        return userOptional.get();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        userRepo.deleteByUsername(username);
    }

    @Override
    public List<User> getByKeyword(String keyword) {
        return userRepo.findByKeyword(keyword);
    }

    @Override
    @Transactional
    public void createAdminUser(String username, String rawPassword,String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(securityService.doBCryptPassEncoding(rawPassword));
        user.setEmail(email);
        user.setEnabled(true);
        userRepo.save(user);

        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority(Role.ROLE_ADMIN);
        authorityRepo.save(authority);
    }

    @Override
    @Transactional
    public void createShopperUser(String username, String rawPassword,String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(securityService.doBCryptPassEncoding(rawPassword));
        user.setEmail(email);
        user.setEnabled(true);
        userRepo.save(user);

        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority(Role.ROLE_SHOPPER);
        authorityRepo.save(authority);

        applicationContext.publishEvent(new UserRegistrationEvent(this, user));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
