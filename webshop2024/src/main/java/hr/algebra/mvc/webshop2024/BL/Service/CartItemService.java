package hr.algebra.mvc.webshop2024.BL.Service;


import hr.algebra.mvc.webshop2024.DAL.Entity.CartItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.Product;
import hr.algebra.mvc.webshop2024.DAL.Entity.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    List<CartItem> findAll();
    CartItem findById(long id);
    Optional<CartItem> findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    CartItem save(CartItem obj);
    void deleteById(long id);
}
