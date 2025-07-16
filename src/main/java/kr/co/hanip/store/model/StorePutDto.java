package kr.co.hanip.store.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class StorePutDto {
    private int userId;
    private int storeId;
    private String category;
    private String name;
    private String comment;
    private String businessNumber;
    private String licensePath;
    private String address;
}
