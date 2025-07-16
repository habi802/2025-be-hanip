package kr.co.hanip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserJoinDto {
    private int userId;
    private String name;
    private String loginId;
    private String loginPw;
    private String address;
    private String phone;
    private String email;
    private String imagePath;
    private String role;
}
