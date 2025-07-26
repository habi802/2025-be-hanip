package kr.co.hanip.customer.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomerLoginReq {
    private String loginId;
    private String loginPw;
}
