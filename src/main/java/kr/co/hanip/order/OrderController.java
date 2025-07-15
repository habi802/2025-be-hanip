package kr.co.hanip.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;


    //요구사항명세서 : order-주문등록
    @PostMapping("/order")
    public ResponseEntity<?> saveOrder(HttpServletRequest httpReq, @RequestBody OrderPostReq req) {
        // (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME); , 일단 멤버아이디1
        int logginedMemberId = 2;
        log.info("req: {}", req);
        int result = orderService.saveOrder(req, logginedMemberId);
        return ResponseEntity.ok(result);
        //return new ResponseEntity<>(HttpStatus.OK);
    }
}
