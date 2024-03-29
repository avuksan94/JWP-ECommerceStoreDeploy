package hr.algebra.mvc.webshop2024.API;

import hr.algebra.mvc.webshop2024.BL.Service.CartItemService;
import hr.algebra.mvc.webshop2024.BL.Service.ShoppingCartService;
import hr.algebra.mvc.webshop2024.DAL.Entity.CartItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.ShoppingCart;
import hr.algebra.mvc.webshop2024.DTO.DTOCartItem;
import hr.algebra.mvc.webshop2024.Mapper.CartItemMapper;
import hr.algebra.mvc.webshop2024.Utils.CustomExceptions.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/webShopApi")
public class ShoppingRestController {
    private final CartItemService cartItemService;
    private final ShoppingCartService shoppingCartService;
    private final CartItemMapper cartItemMapper;

    public ShoppingRestController(CartItemService cartItemService, ShoppingCartService shoppingCartService, CartItemMapper cartItemMapper) {
        this.cartItemService = cartItemService;
        this.shoppingCartService = shoppingCartService;
        this.cartItemMapper = cartItemMapper;
    }

    @Async
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartItems/allCartItems")
    public CompletableFuture<List<DTOCartItem>> findAllCartItems(){
        return CompletableFuture.completedFuture(
                cartItemService.findAll()
                        .stream()
                        .map(cartItemMapper::CartItemToDTOCartItem)
                        .collect(Collectors.toList())
        );
    }

    @Async
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartItems/findByShoppingCartId/{shoppingCartId}")
    public CompletableFuture<List<DTOCartItem>> findAllCartItemsForShoppingCart(@PathVariable int shoppingCartId) {
        ShoppingCart shoppingCartForShopper = shoppingCartService.findById(shoppingCartId);
        List<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCartForShopper);
        if (cartItems == null) {
            throw new CustomNotFoundException("Shopping cart not found for id: " + shoppingCartId);
        }

        return CompletableFuture.completedFuture(
                         cartItems
                        .stream()
                        .map(cartItemMapper::CartItemToDTOCartItem)
                        .collect(Collectors.toList())
        );
    }

    @Async
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartItems/findBySession/{session}")
    public CompletableFuture<List<DTOCartItem>> findAllCartItemsForSession(@PathVariable String session) {
        return shoppingCartService.findBySessionId(session)
                .map(shoppingCart ->
                        CompletableFuture.completedFuture(
                                cartItemService.findByShoppingCart(shoppingCart)
                                        .stream()
                                        .map(cartItemMapper::CartItemToDTOCartItem)
                                        .collect(Collectors.toList())
                        )
                )
                .orElseThrow(() -> new CustomNotFoundException("Shopping cart not found for session: " + session));
    }

    @Async
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartItems/findByUsername/{username}")
    public CompletableFuture<List<DTOCartItem>> findAllCartItemsForUsername(@PathVariable String username) {
        return shoppingCartService.findByUsername(username)
                .map(shoppingCart ->
                        CompletableFuture.completedFuture(
                                cartItemService.findByShoppingCart(shoppingCart)
                                        .stream()
                                        .map(cartItemMapper::CartItemToDTOCartItem)
                                        .collect(Collectors.toList())
                        )
                )
                .orElseThrow(() -> new CustomNotFoundException("Shopping cart not found for user: " + username));
    }
}
