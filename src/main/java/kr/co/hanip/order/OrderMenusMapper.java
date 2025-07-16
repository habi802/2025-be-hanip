package kr.co.hanip.order;

import kr.co.hanip.order.model.OrderMenuPostDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMenusMapper {
    int SaveQuantity(OrderMenuPostDto req);

}
