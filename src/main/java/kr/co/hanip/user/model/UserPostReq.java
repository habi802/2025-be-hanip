package kr.co.hanip.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPostReq {
    private String loginId;
    private String role;
}
