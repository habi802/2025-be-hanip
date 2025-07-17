package kr.co.hanip.menu.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuGetListRes {
    private int menuId;
    private int storeId;
    private String name;
    private String comment;
    private int price;
    private String imagePath;
}
