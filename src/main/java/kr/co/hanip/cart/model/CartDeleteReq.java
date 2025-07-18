package kr.co.hanip.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@AllArgsConstructor

public class CartDeleteReq {
    private int cartId;
    private int userId;

}
