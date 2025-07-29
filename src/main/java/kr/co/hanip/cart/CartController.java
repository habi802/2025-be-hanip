package kr.co.hanip.cart;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.cart.model.CartDeleteReq;
import kr.co.hanip.cart.model.CartListGetRes;
import kr.co.hanip.cart.model.CartPatchReq;
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

    @PostMapping
    public ResponseEntity<ResultResponse<Integer>> save(HttpServletRequest httpReq, @RequestBody CartPostReq req) {
        log.info("req: ", req);
        Integer loggedInUserId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (loggedInUserId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "로그인 후 이용해주세요."));
        }

        req.setUserId(loggedInUserId);
        int result = cartService.save(req);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ResultResponse<List<CartListGetRes>>> findAll(HttpServletRequest httpReq) {
        Integer loggedInUserId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (loggedInUserId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "로그인 후 이용해주세요."));
        }

        List<CartListGetRes> result = cartService.findAll(loggedInUserId);
        if (result == null || result.size() == 0) {
            return ResponseEntity.ok(ResultResponse.fail(400, "조회 실패"));
        }
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @PatchMapping
    public ResponseEntity<ResultResponse<Integer>> updateQuantity(HttpServletRequest httpReq, @RequestBody CartPatchReq req) {
        Integer loggedInUserId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (loggedInUserId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "로그인 후 이용해주세요."));
        }

        int result = cartService.updateQuantity(req, loggedInUserId);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<ResultResponse<Integer>> deleteByCartId(HttpServletRequest httpReq, @PathVariable int cartId) {
        Integer loggedInUserId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (loggedInUserId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "로그인 후 이용해주세요."));
        }

        CartDeleteReq req = new CartDeleteReq(cartId, loggedInUserId);
        int result = cartService.delete(req);

        if (result == 1) {
            return ResponseEntity.ok(ResultResponse.success(result));
        }
        return ResponseEntity.ok(ResultResponse.fail(400, "삭제 실패"));
    }

    @DeleteMapping
    public ResponseEntity<ResultResponse<Integer>> deleteByAllUserId(HttpServletRequest httpReq) {
        Integer loggedInUserId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (loggedInUserId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "로그인 후 이용해주세요."));
        }

        int result = cartService.deleteAll(loggedInUserId);
        if (result == 1) {
            return ResponseEntity.ok(ResultResponse.success(result));
        }
        return ResponseEntity.ok(ResultResponse.fail(400, "삭제 실패"));
    }

}