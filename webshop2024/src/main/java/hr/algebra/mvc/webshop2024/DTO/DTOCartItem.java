package hr.algebra.mvc.webshop2024.DTO;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class DTOCartItem {
    private Long cartItemId;
    private DTOShoppingCart shoppingCart;
    private DTOProduct product;
    private Integer quantity;

    public DTOCartItem(DTOShoppingCart shoppingCart, DTOProduct product, Integer quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }
}
