package kr.co.hanip.order;

import kr.co.hanip.order.model.OrderGetRes;
import kr.co.hanip.order.model.OrderPostDto;
import kr.co.hanip.order.model.OrderStatusPatchReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto req);
    List<OrderGetRes> findByOrderIdAndUserId(int userId);
    int updateStatus(OrderStatusPatchReq req);
    int hideByOrderId(int orderId);
}
