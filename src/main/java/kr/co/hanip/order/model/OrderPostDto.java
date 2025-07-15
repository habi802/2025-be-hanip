package kr.co.hanip.order.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPostDto {
    private int user_id;
    private int store_id;
    private String address;
    private int amount;


}
