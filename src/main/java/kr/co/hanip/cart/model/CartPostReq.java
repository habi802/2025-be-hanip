package kr.co.hanip.cart.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartPostReq {
    private int cartId;
    private int userId;
    private int menuId;
}
