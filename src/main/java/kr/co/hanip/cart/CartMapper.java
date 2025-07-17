package kr.co.hanip.cart;

import kr.co.hanip.cart.model.CartDeleteReq;
import kr.co.hanip.cart.model.CartListGetRes;
import kr.co.hanip.cart.model.CartPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int save(CartPostReq req);
    List<CartListGetRes> findAllMenuAndUserId(int userId);
    int deleteByCartId(CartDeleteReq req);
    int deleteByAllUserId(int userId);
}
