package kr.co.hanip.cart.model;

import lombok.Getter;
import lombok.Setter;

@Getter

public class CartPostReq {
    private int userId;
    private int menuId;
    private int quantity;
}
