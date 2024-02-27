package hr.algebra.mvc.webshop2024.DTO;

import hr.algebra.mvc.webshop2024.DAL.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class DTOAuthority {
    private Long id;
    private DTOUser user;
    private Role authority;

    public DTOAuthority(DTOUser user, Role authority) {
        this.user = user;
        this.authority = authority;
    }
}
