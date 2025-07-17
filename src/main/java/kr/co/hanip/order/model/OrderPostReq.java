package kr.co.hanip.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderPostReq {
    private int userId;
    private int storeId;
    private String address;
    private int amount;
    @JsonProperty("menu_ids")
    private List<Integer> menuIds;
    private List<Integer> quantities;

}
