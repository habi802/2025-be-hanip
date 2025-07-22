package kr.co.hanip.order.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPostDto {
    private int id;
    private int userId;
    private int storeId;
    private String address;
    private int amount;


}
