package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StorePostReq {
    private int userId;
    private String category;
    private String name;
    private String comment;
    private String businessNumber;
    private String licensePath;
    private String address;
    private String tel;
    private String ownerName;
}
