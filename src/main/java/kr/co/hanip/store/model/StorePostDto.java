package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StorePostDto {
    private int id;          // PK
    private int userId;      // FK (user_id)
    private int categoryId;
    private String name;
    private String comment;
    private String businessNumber;
    private String licensePath;
    private String address;
    private String ownerName;
}
