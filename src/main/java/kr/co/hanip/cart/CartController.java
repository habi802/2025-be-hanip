package kr.co.hanip.cart;

import kr.co.hanip.cart.model.CartDeleteReq;
import kr.co.hanip.cart.model.CartListGetRes;
import kr.co.hanip.cart.model.CartPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<?> save(@RequestBody CartPostReq req) {
        log.info("req: {}", req);
        int result = cartService.save(req);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<CartListGetRes>> findAll(@RequestParam int userId) {
        log.info(" userId: {}", userId);
        List<CartListGetRes> result = cartService.findAll(userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteByCartId(@PathVariable int cartId, @RequestParam int userId) {
        CartDeleteReq req = new CartDeleteReq(cartId, userId);
        int result = cartService.delete(req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByAllUserId(@RequestParam int userId) {
        int result = cartService.deleteAll(userId);
        return ResponseEntity.ok(result);
    }

}
