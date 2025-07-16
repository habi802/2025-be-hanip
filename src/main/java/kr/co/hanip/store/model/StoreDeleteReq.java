package kr.co.hanip.store.model;

import lombok.*;

@Getter
@Setter
@ToString
public class StoreDeleteReq {
    private int storeId;
    private String password;
}
