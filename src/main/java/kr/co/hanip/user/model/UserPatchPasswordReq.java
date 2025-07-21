package kr.co.hanip.user.model;

import lombok.Getter;

@Getter
public class UserPatchPasswordReq {
    private String loginPw;
    private String newLoginPw;
}
