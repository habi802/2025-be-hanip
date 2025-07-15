package kr.co.hanip.order.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderPostReq {
    private int user_id;
    private int store_id;
    private String address;
    private int amount;
}
