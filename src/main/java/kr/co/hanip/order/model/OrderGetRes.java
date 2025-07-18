package kr.co.hanip.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.apache.ibatis.annotations.Property;

@Getter
@ToString
public class OrderGetRes {
    private int orderId; // o.id
    private int userId; // o.user_id
    private String storeName; //s.`name`
    private String menuName; // m.`name`
    private int quantity; //om.quantity
    private int price; //m.price
    private int amount; // o.amount
    private String status; // o.`status`


}
