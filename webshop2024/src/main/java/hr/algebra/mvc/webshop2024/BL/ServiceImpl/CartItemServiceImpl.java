package hr.algebra.mvc.webshop2024.BL.ServiceImpl;

import hr.algebra.mvc.webshop2024.BL.Service.CartItemService;
import hr.algebra.mvc.webshop2024.DAL.Entity.CartItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;
import hr.algebra.mvc.webshop2024.DAL.Entity.ShoppingCart;
import hr.algebra.mvc.webshop2024.DAL.Repository.CartItemRepository;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepo;

    public CartItemServiceImpl(CartItemRepository cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepo.findAll();
    }

    @Override
    public CartItem findById(long id) {
        Optional<CartItem> cartItemOptional = cartItemRepo.findById(id);

        if (cartItemOptional.isEmpty()){
            throw new CustomNotFoundException("Cart Item id not found - " + id);
        }

        return cartItemOptional.get();
    }

    @Override
    public Optional<CartItem> findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product) {
        return cartItemRepo.findByShoppingCartAndProduct(shoppingCart,product);
    }

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepo.findByShoppingCart(shoppingCart);
    }

    @Override
    @Transactional
    public CartItem save(CartItem obj) {
        return cartItemRepo.save(obj);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<CartItem> checkIfExists = cartItemRepo.findById(id);
        if (checkIfExists.isEmpty()){
            throw new CustomNotFoundException("Cart Item with that ID was not found: " + id);
        }
        cartItemRepo.deleteById(id);
    }
}
