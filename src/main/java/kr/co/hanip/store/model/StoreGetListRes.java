package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreGetListRes {
    private int storeId;
    private String name;
    private String category;
    private int isActive;
    private int favorites;
    private long rating;
    private String imagePath;
}
