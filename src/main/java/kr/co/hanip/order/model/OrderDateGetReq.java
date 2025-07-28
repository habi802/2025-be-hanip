package kr.co.hanip.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDateGetReq {
    int storeId;
    String startDate;
    String endDate;

}
