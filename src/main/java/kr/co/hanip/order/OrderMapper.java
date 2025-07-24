package kr.co.hanip.order;

import kr.co.hanip.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto req);
    List<OrderGetRes> findByOrderIdAndUserIdOrStoreId(int userId);
    int updateStatus(OrderStatusPatchReq req);
    int hideByOrderId(OrderHidePatchDto orderHidePatchDto);
    List<OrderGetDetailRes> findOrderByStoreId(int storeId);
}
