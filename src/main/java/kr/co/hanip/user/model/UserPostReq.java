package kr.co.hanip.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserPostReq {
    private String name;
    private String loginId;
    private String loginPw;
    private String address;
    private String phone;
    private String email;
    private String imagePath;
    private String role;
}
