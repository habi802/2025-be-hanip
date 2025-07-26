package kr.co.hanip.customer;

import kr.co.hanip.customer.model.CustomerJoinReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    int save(CustomerJoinReq req);
}
