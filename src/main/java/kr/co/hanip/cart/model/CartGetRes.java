package kr.co.hanip.cart.model;

import lombok.Getter;

@Getter

public class CartGetRes {
    private int id;
    private int userId;
    private int menuId;
    private int quantity;
    private String created;
    private String updated;
}
