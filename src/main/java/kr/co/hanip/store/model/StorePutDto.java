package kr.co.hanip.store.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class StorePutDto {
    private int userId;
    private int storeId;
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
    private String password;
    private String imagePath;
    private String phone;
    private String email;
}
