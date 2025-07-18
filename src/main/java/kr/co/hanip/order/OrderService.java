package kr.co.hanip.order;

import kr.co.hanip.menu.MenuMapper;
import kr.co.hanip.menu.model.MenuGetRes;
import kr.co.hanip.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderMenusMapper orderMenusMapper;
    private final MenuMapper menuMapper;

    // ----------요구사항명세서 : order-주문등록-------------
    @Transactional
    public int saveOrder(OrderPostReq req, int logginedMemberId) {
        //상품 정보 DB로 부터 가져오기.
        //List<MenuGetListRes> menuList = menuMapper.menuGetList(req.getMenuIds());


        // 구매 총금액 (menu 전달 받고 구현예정)
        int amount = 0;
        for (OrderMenuVo item : req.getOrders()) {
            MenuGetRes menu = menuMapper.menuGetOne(item.getMenuId());

            amount += menu.getPrice() * item.getQuantity();  //수량 x 메뉴가격 예정
        }
        log.info("amount={}", amount);


        OrderPostDto orderPostDto = new OrderPostDto();
        orderPostDto.setUserId(logginedMemberId);
        orderPostDto.setStoreId(req.getStoreId());
        orderPostDto.setAddress(req.getAddress());
        orderPostDto.setAmount(amount);
        log.info("orderPostDto={}", orderPostDto);
        orderMapper.save(orderPostDto); // 이 시점에 orderPostDto.getId() 사용 가능 (주문 먼저 저장 → ID 채워짐 (Auto Increment)


        // OrderMenuPostDto 생성 , orders에 등록후 해당 id를 Dto에 담아 orderMenus로 전달
        OrderMenuPostDto orderMenuPostDto = new OrderMenuPostDto();
        orderMenuPostDto.setOrderId(orderPostDto.getId());
        orderMenuPostDto.setMenuId(req.getOrders());

        log.info("orderMenuPostDto={}", orderMenuPostDto);

        // 메뉴 저장
        orderMenusMapper.SaveQuantity(orderMenuPostDto);

        //장바구니삭제 (cart전달받고 작업예정)
        //cartMapper.deleteByMemberId(logginedMemberId);

        return 1;

    }


    // ------------------주문 조회 GET--------------------
    public List<OrderGetRes> getOrderList(int userId) {
        return orderMapper.findByOrderIdAndUserId(userId);
    }

    // ------------------주문상태수정--------------------
    public int modifyOrderStatus(OrderStatusPatchReq req) {
        return orderMapper.updateStatus(req);
    }

    // ---------------주문 삭제 ----------------------
    public int hideByOrderId(int logginedMemberId, int orderId) {

        OrderHidePatchDto orderHidePatchDto = new OrderHidePatchDto();
        orderHidePatchDto.setUserId(logginedMemberId);
        orderHidePatchDto.setOrderId(orderId);
        log.info("orderHidePatchDto={}", orderHidePatchDto);

        return orderMapper.hideByOrderId(orderHidePatchDto);
    }

}
