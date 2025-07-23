package kr.co.hanip.cart.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartPatchDto {
    private int cartId;
    private int userId;
    private int quantity;
}
