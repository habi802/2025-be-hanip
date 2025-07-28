package kr.co.hanip.store.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class StorePostReq {
    private int userId;
    private String category;
    private String name;
    private String comment;
    private String businessNumber;
    private String licensePath;
    private String postcode;
    private String address;
    private String addressDetail;
    private String tel;
    private String ownerName;
}
