package kr.co.hanip.menu.model;

import lombok.Getter;

@Getter
public class MenuGetRes {
    private int menuId;
    private int storeId;
    private String name;
    private String comment;
    private int price;
    private String imagePath;
}
