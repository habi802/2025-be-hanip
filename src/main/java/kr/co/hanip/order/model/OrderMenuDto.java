package kr.co.hanip.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuDto {
    private int menuId;
    private int quantity;
}
