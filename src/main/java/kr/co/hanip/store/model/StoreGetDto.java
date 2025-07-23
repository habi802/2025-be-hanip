package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreGetDto {
    private int id;
    private int userId;
    private String category;
    private String name;
    private String comment;
    private String address;
    private String tel;
    private String ownerName;
    private int isActive;
    private String imagePath;
    private String phone;
    private String email;
}
