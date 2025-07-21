package kr.co.hanip.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRes {
    private int id;
    private int storeId;
    @JsonIgnore
    private String loginPw;
}