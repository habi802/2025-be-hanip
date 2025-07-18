package kr.co.hanip.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class OrderMenuPostDto {
    private int orderId;
    private List<OrderMenuVo> menuId;
}
