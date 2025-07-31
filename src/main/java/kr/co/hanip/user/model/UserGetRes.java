package kr.co.hanip.user.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserGetRes {
    private int id;
    private String name;
    private String loginId;
    private String postcode;
    private String address;
    private String addressDetail;
    private String phone;
    private String email;
    private String imagePath;
    private String role;
    private String created;
}