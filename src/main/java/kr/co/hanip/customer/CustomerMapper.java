package kr.co.hanip.customer;

import kr.co.hanip.customer.model.CustomerGetRes;
import kr.co.hanip.customer.model.CustomerJoinReq;
import kr.co.hanip.customer.model.CustomerLoginReq;
import kr.co.hanip.customer.model.CustomerLoginRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    int save(CustomerJoinReq req);
    Integer findIdByLoginId(String loginId);
    CustomerLoginRes findByLoginId(CustomerLoginReq req);
    CustomerGetRes findById(int customerId);
}
