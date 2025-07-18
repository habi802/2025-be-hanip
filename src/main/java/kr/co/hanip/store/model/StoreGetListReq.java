package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Setter
@ToString
public class StoreGetListReq {
    private String searchText;
    private String category;

    @ConstructorProperties({"category", "search_text"})
    public StoreGetListReq(String category, String searchText) {
        this.category = category;
        this.searchText = searchText;
    }
}
