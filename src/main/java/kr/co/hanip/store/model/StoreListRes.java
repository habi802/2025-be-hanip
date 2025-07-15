package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreListRes {
    private int storeId;
    private String name;
    private String category;
    private int likeCount;
    private long avgRating;
}
