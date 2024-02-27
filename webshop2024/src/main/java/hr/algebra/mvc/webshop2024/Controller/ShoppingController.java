package hr.algebra.mvc.webshop2024.Controller;

import hr.algebra.mvc.webshop2024.BL.Service.CartItemService;
import hr.algebra.mvc.webshop2024.BL.Service.ProductImageService;
import hr.algebra.mvc.webshop2024.BL.Service.ShoppingCartService;
import hr.algebra.mvc.webshop2024.DAL.Consts.WebShopConsts;
import hr.algebra.mvc.webshop2024.DAL.Entity.CartItem;
import hr.algebra.mvc.webshop2024.DAL.Entity.ProductImage;
import hr.algebra.mvc.webshop2024.DAL.Entity.ShoppingCart;
import hr.algebra.mvc.webshop2024.ViewModel.CartItemVM;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("webShop")
public class ShoppingController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;
    private final ProductImageService productImageService;

    public ShoppingController(ShoppingCartService shoppingCartService, CartItemService cartItemService, ProductImageService productImageService) {
        this.shoppingCartService = shoppingCartService;
        this.cartItemService = cartItemService;
        this.productImageService = productImageService;
    }


    @PostMapping("/shopping/addToCart")
    @ResponseBody
    public ResponseEntity<?> addItemToCart(@RequestParam("productId") Long productId,
                                           @RequestParam("quantity") Integer quantity,
                                           HttpServletRequest request, Principal principal) {
        String identifier = principal != null ? principal.getName() : request.getSession().getId();
        boolean isUserRegistered = principal != null;

        try {
            CompletableFuture<Void> addItemFuture = CompletableFuture.runAsync(() ->
                    shoppingCartService.addItemToCart(identifier, productId, quantity, isUserRegistered));
            addItemFuture.join();

            return ResponseEntity.ok().body(Map.of("success", true, "message", "Item added to cart successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", "Failed to add item to cart"));
        }
    }

    @PostMapping("/shopping/removeFromCartCart")
    @ResponseBody
    public ResponseEntity<?> removeItemFromCart(@RequestParam("productId") Long productId,
                                                @RequestParam("quantity") Integer quantity,
                                                HttpServletRequest request, Principal principal) {
        String identifier = principal != null ? principal.getName() : request.getSession().getId();
        boolean isUserRegistered = principal != null;

        try {
            CompletableFuture<Void> removeItemFuture = CompletableFuture.runAsync(() ->
                    shoppingCartService.removeItemFromCart(identifier, productId, quantity, isUserRegistered));
            removeItemFuture.join();

            return ResponseEntity.ok().body(Map.of("success", true, "message", "Item removed from cart successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", "Failed to remove item from cart"));
        }
    }

    @PostMapping("/shopping/changeQuantity")
    public ResponseEntity<?> changeQuantityInCart(@RequestParam("productId") Long productId,
                                                  @RequestParam("quantity") Integer quantity,
                                                  HttpServletRequest request, Principal principal) {
        String identifier = principal != null ? principal.getName() : request.getSession().getId();
        boolean isUserRegistered = principal != null;

        CompletableFuture<Void> changeQuantityFuture = CompletableFuture.runAsync(() -> {
            if (quantity > 0) {
                shoppingCartService.addItemToCart(identifier, productId, quantity, isUserRegistered);
            } else if (quantity < 0) {
                shoppingCartService.removeItemFromCart(identifier, productId, Math.abs(quantity), isUserRegistered);
            }
        });
        try {
            changeQuantityFuture.join();
            return ResponseEntity.ok().body(Map.of("success", true, "message", "Cart updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Failed to update cart"));
        }
    }

    @GetMapping("/shopping/cartItemCount")
    @ResponseBody
    public int getCartItemCount(HttpServletRequest request,Principal principal) {
        boolean isRegistered = false;
        if (principal != null) {
            isRegistered = true;
            return shoppingCartService.getCartItemCount(principal.getName(), isRegistered);
        }
        return shoppingCartService.getCartItemCount(request.getSession().getId(), isRegistered);
    }

    @GetMapping("/shopping/cart")
    public String viewShoppingCart(Model model, HttpServletRequest request,Principal principal) {
        String identifier = principal != null ? principal.getName() : request.getSession().getId();
        boolean isUserRegistered = principal != null;

        CompletableFuture<Optional<ShoppingCart>> shoppingCartFuture = isUserRegistered
                ? CompletableFuture.supplyAsync(() -> shoppingCartService.findByUsername(identifier))
                : CompletableFuture.supplyAsync(() -> shoppingCartService.findBySessionId(identifier));

        CompletableFuture<List<ProductImage>> productImagesFuture = CompletableFuture.supplyAsync(() ->
                productImageService.findAll());

        shoppingCartFuture.thenAcceptAsync(shoppingCart -> {
            if (shoppingCart.isPresent()) {
                List<CartItem> realCartItems = cartItemService.findByShoppingCart(shoppingCart.get());
                List<CartItemVM> cartItems = new ArrayList<>();

                // Synchronously wait for product images to ensure they are available for processing cart items.
                List<ProductImage> productImages = productImagesFuture.join();

                for (CartItem realCartItem : realCartItems) {
                    String imageLink = productImages.stream()
                            .filter(image -> Objects.equals(image.getProduct().getProductId(), realCartItem.getProduct().getProductId()))
                            .findFirst()
                            .map(image -> image.getImage().getImageUrl())
                            .orElse(WebShopConsts.DEFAULT_IMAGE_FILENAME);

                    CartItemVM cartItemVM = new CartItemVM(
                            realCartItem.getCartItemId(),
                            realCartItem.getShoppingCart().getCartId(),
                            realCartItem.getProduct().getProductId(),
                            realCartItem.getProduct().getName(),
                            realCartItem.getProduct().getPrice(),
                            realCartItem.getQuantity(),
                            imageLink);

                    cartItems.add(cartItemVM);
                }

                model.addAttribute("cartItems", cartItems);
            }
        }).join();

        if (shoppingCartFuture.isCompletedExceptionally()) {
            return "error";
        }

        return shoppingCartFuture.isDone() && shoppingCartFuture.join().isPresent()
                ? "shoppingcarts/shoppingcart-display"
                : "redirect:/webShop/products/list";
    }
}
