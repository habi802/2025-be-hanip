package kr.co.hanip.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPatchPasswordDto {
    private int userId;
    private String newLoginPw;
}
