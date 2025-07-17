package kr.co.hanip.cart;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.cart.model.CartDeleteReq;
import kr.co.hanip.cart.model.CartListGetRes;
import kr.co.hanip.cart.model.CartPostReq;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.user.etc.UserConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;

//    @PostMapping
//    public ResponseEntity<?> save(HttpServletRequest httpReq, @RequestBody CartPostReq req) {
//        log.info("req: {}", req);
//        int LoggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
//        req.setUserId(LoggedInUserId);
//        int result = cartService.save(req);
//        return ResponseEntity.ok(ResultResponse.success(result));
//    }
    @PostMapping
    public ResponseEntity<ResultResponse<Integer>> save(HttpServletRequest httpReq, @RequestBody CartPostReq req) {
        log.info("req: {}", req);
        Integer loggedInUserId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (loggedInUserId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(400, "등록실패"));
        }

        req.setUserId(loggedInUserId);
        int result = cartService.save(req);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<List<CartListGetRes>> findAll(@RequestParam int userId) {
        log.info(" userId: {}", userId);
        List<CartListGetRes> result = cartService.findAll(userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Integer> deleteByCartId(@PathVariable int cartId, @RequestParam int userId) {
        CartDeleteReq req = new CartDeleteReq(cartId, userId);
        int result = cartService.delete(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteByAllUserId(@RequestParam int userId) {
        int result = cartService.deleteAll(userId);
        return ResponseEntity.ok(result);
    }

}
