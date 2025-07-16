package kr.co.hanip.order;

import kr.co.hanip.order.model.OrderPostDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto req);

}
