package kr.co.hanip.cart;

import kr.co.hanip.cart.model.CartGetRes;
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
    public ResponseEntity<List<CartGetRes>> findAll() {
        return null;
    }
}
