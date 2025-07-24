package kr.co.hanip.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderGetListReq {
    private int menuName;
    private int quantity;
    private int price;
}
