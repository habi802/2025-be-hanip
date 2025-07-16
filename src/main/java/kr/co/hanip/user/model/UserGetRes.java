package kr.co.hanip.user.model;

import lombok.Getter;

@Getter
public class UserGetRes {
    private int id;
    private String name;
    private String loginId;
    private String address;
    private String phone;
    private String email;
    private String imagePath;
    private String role;
    private String created;
}
