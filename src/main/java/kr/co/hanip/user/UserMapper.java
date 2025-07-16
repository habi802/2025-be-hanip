package kr.co.hanip.user;

import kr.co.hanip.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(UserJoinDto dto);
    UserLoginRes findByLoginId(UserLoginReq req);
    UserGetRes findByUserId(int userId);
    String findPasswordByUserId(int userId);
    int update(UserUpdateDto dto);
}
