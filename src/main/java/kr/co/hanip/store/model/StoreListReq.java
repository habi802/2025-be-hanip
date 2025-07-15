package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Setter
@ToString
public class StoreListReq {
    private String searchText;
    private String categoryId;

    @ConstructorProperties({"category_id", "search_text"})
    public StoreListReq(String categoryId, String searchText) {
        this.categoryId = categoryId;
        this.searchText = searchText;
    }
}
