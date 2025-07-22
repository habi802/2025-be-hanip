package kr.co.hanip.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuVo {
    //OrderMenuPostDto.orders
    private int menuId;
    private int quantity;
}
