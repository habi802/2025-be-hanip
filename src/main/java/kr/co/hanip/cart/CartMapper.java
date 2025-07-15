package kr.co.hanip.cart;

import kr.co.hanip.cart.model.CartGetRes;
import kr.co.hanip.cart.model.CartPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int save(CartPostReq req);
    List<CartGetRes> findAllItemAndUserId(int userId);
}
