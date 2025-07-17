package kr.co.hanip.menu.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuGetRes {
    private Integer menuId;
    private int storeId;
    private String name;
    private String comment;
    private int price;
    private String imagePath;
}
