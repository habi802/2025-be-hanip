package kr.co.hanip.user;

import kr.co.hanip.user.model.UserGetRes;
import kr.co.hanip.user.model.UserPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(UserPostReq req);
    UserGetRes findByUserId(int userId);
}
