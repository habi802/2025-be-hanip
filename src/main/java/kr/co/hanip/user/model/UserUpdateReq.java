package kr.co.hanip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserUpdateReq {
    private String loginPw;
    private String newLoginPw;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String imagePath;
}
