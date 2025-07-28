package kr.co.hanip.order;

import kr.co.hanip.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto req);
    List<OrderGetRes> findByOrderIdAndUserId(int userId);
    List<OrderGetReq> findById(int orderId);
    int updateStatus(OrderStatusPatchReq req);
    int hideByOrderId(int orderId);
    List<OrderGetDetailRes> findOrderByStoreId(int storeId);
}
