package kr.co.hanip.cart;

import kr.co.hanip.cart.model.CartGetRes;
import kr.co.hanip.cart.model.CartPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    public int save(CartPostReq req) {
        return cartMapper.save(req);
    }

    public List<CartGetRes> findAll(int userId) {
        return cartMapper.findAllItemAndUserId(userId);
    }
}
