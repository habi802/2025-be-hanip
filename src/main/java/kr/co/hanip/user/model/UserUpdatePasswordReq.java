package kr.co.hanip.user.model;

import lombok.Getter;

@Getter
public class UserUpdatePasswordReq {
    private String loginPw;
    private String newLoginPw;
}
