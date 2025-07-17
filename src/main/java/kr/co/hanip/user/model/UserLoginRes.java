package kr.co.hanip.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class UserLoginRes {
    private int id;
    @JsonIgnore
    private String loginPw;
}