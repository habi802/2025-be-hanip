package kr.co.hanip.cart;

import kr.co.hanip.cart.model.CartDeleteReq;
import kr.co.hanip.cart.model.CartListGetRes;
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

    public List<CartListGetRes> findAll(int userId) {
        return cartMapper.findAllByUserId(userId);
    }

    public int delete(CartDeleteReq req) {
        return cartMapper.deleteByCartId(req);
    }

    public int deleteAll(int userId) {
        return cartMapper.deleteByUserId(userId);
    }

}
