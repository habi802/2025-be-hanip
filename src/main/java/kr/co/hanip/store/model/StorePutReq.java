package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class StorePutReq {
    private int storeId;
    private String category;
    private String name;
    private String comment;
    private String businessNumber;
    private String licensePath;
    private String address;
}
