package kr.co.hanip.customer.model;

import lombok.Getter;

@Getter
public class CustomerGetRes {
    private int id;
    private String name;
    private String loginId;
    private String postcode;
    private String address;
    private String addressDetail;
    private String email;
    private String tel;
    private String phone;
    private String imagePath;
    private String imageName;
}