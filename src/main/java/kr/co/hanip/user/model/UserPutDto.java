package kr.co.hanip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserPutDto {
    private int userId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String imagePath;
}
