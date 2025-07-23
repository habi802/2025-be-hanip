package kr.co.hanip.cart;

import kr.co.hanip.cart.model.*;
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
        int result = cartMapper.save(req);
        return req.getCartId();
    }

    public List<CartListGetRes> findAll(int userId) {
        return cartMapper.findAllByUserId(userId);
    }

    public int updateQuantity(CartPatchReq req, int userId) {
        CartPatchDto dto = CartPatchDto.builder()
                .cartId(req.getCartId())
                .userId(userId)
                .quantity(req.getQuantity())
                .build();

        return cartMapper.updateQuantityByCartIdAndUserId(dto);
    }

    public int delete(CartDeleteReq req) {
        return cartMapper.deleteByCartId(req);
    }

    public int deleteAll(int userId) {
        return cartMapper.deleteByUserId(userId);
    }

}
