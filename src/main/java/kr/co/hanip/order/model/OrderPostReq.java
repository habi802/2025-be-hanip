package kr.co.hanip.order.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderPostReq {
    private int userId;
    private int storeId;
    private String address;
    private int amount;
    private List<Integer> menuId;
    private List<Integer> quantity;

}
