package kr.co.hanip.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.order.model.OrderGetRes;
import kr.co.hanip.order.model.OrderPostReq;
import kr.co.hanip.order.model.OrderStatusPatchReq;
import kr.co.hanip.user.etc.UserConstants;
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
    public ResponseEntity<ResultResponse<Integer>> saveOrder(HttpServletRequest httpReq , @RequestBody OrderPostReq req) {
        int sessionId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int logginedMemberId = sessionId;  // == true ? sessionId : 2 ; //일단 유저아이디 임의숫자
        log.info("req: {}", req);
        int result = orderService.saveOrder(req, logginedMemberId);

        return ResponseEntity.ok(ResultResponse.success(result));
        //return new ResponseEntity<>(HttpStatus.OK);
    }



    // ----------요구사항명세서 : order-주문조회-------------
    @GetMapping("/order")
    public ResponseEntity<ResultResponse<List<OrderGetRes>>> getOrderListByUserId(HttpServletRequest httpReq) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        log.info("userId: {}", logginedMemberId);
        List<OrderGetRes> result = orderService.getOrderList(logginedMemberId);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    // ---------- order-주문상세조회-------------
    //일단 위에랑 같음

    //ResultResponse<>
    // ----------- order-주문상태수정-------------
    @PatchMapping("/order/status")
    public ResponseEntity<ResultResponse<Integer>> modifyStatus(@RequestBody OrderStatusPatchReq req) {
        int result = orderService.modifyOrderStatus(req);
        return ResponseEntity.ok(ResultResponse.success(result));
    }


    // ----------- order-주문삭제 --------------
    @PatchMapping("/order/{orderId}")
    public ResponseEntity<ResultResponse<Integer>> modifyOrderStatus(HttpServletRequest httpReq , @PathVariable int orderId) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int result = orderService.hideByOrderId(logginedMemberId,orderId);
        return ResponseEntity.ok(ResultResponse.success(result));
    }



}
