package kr.co.hanip.store.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Setter
@ToString
public class StoreGetListDto {
    private String searchText;
    private String category;
    private Integer userId;

}