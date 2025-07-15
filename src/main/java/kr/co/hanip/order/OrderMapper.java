package kr.co.hanip.order;

import kr.co.hanip.order.model.OrderPostDto;
import kr.co.hanip.order.model.OrderPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int save(OrderPostReq dto);
}
