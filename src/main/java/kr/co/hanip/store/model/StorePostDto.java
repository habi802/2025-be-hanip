package kr.co.hanip.store.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class StorePostDto {
    private int userId;      // FK (user_id)
    private String category;
    private String name;
    private String comment;
    private String businessNumber;
    private String licensePath;
    private String address;
    private String tel;
    private String ownerName;
}
