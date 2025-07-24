package kr.co.hanip.cart;

import kr.co.hanip.cart.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int save(CartPostReq req);
    List<CartListGetRes> findAllMenuAndUserId(int userId);
    int updateQuantityByCartIdAndUserId(CartPatchDto dto);
    int deleteByCartId(CartDeleteReq req);
    int deleteByAllUserId(int userId);
}