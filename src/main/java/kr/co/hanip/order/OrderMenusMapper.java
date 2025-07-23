package kr.co.hanip.order;

import kr.co.hanip.order.model.OrderMenuDto;
import kr.co.hanip.order.model.OrderMenuPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMenusMapper {
    int SaveQuantity(OrderMenuPostDto req);
    List<OrderMenuDto> findAllByOrderId(int orderId);
}
