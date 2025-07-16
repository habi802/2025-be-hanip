package kr.co.hanip.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class StoreDeleteReq {
    private int storeId;
    private int userId;
}
