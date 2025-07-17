package kr.co.hanip.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdatePasswordDto {
    private int userId;
    private String newLoginPw;
}
