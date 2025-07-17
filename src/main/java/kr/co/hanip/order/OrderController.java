package kr.co.hanip.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.order.model.OrderGetRes;
import kr.co.hanip.order.model.OrderPostReq;
import kr.co.hanip.order.model.OrderStatusPatchReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Property;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;


    //----------요구사항명세서 : order-주문등록-------------
    @PostMapping("/order")
    public ResponseEntity<?> saveOrder(@RequestBody OrderPostReq req) {
        // (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME); , 일단 멤버아이디 임의숫자
        int logginedMemberId = 2;
        log.info("req: {}", req);
        int result = orderService.saveOrder(req, logginedMemberId);

        return ResponseEntity.ok(result);
        //return new ResponseEntity<>(HttpStatus.OK);
    }



    // ----------요구사항명세서 : order-주문조회-------------
    @GetMapping("/order")
    //                                                세션으로 바꿀예정
    public ResponseEntity<?> getOrderListByUserId(@RequestParam int userId) {
        int logginedMemberId = userId;
        log.info("userId: {}", userId);
        List<OrderGetRes> result = orderService.getOrderList(logginedMemberId);
        return ResponseEntity.ok(result);
    }

    // ---------- order-주문상세조회-------------
    //일단 위에랑 같음


    // ----------- order-주문상태수정-------------
    @PatchMapping("/order/status")
    public ResponseEntity<?> modifyStatus(@RequestBody OrderStatusPatchReq req) {
        int result = orderService.modifyOrderStatus(req);
        return ResponseEntity.ok(result);
    }


    // ----------- order-주문삭제 --------------
    @PatchMapping("/order/{orderId}")
    public ResponseEntity<?> modifyOrderStatus(@PathVariable int orderId) {
        int result = orderService.hideByOrderId(orderId);
        return ResponseEntity.ok(result);
    }



}
