package kr.co.hanip.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuDeleteReq {
    private int menuId;
    private int userId;
}
