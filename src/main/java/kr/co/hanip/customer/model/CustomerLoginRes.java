package kr.co.hanip.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomerLoginRes {
    private int id;
    @JsonIgnore
    private String loginPw;
}
