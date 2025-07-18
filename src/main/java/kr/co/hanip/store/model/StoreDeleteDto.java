package kr.co.hanip.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreDeleteDto {
    private int storeId;
    private int userId;
    private String password;
}
