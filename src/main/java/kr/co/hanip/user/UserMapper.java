package kr.co.hanip.user;

import kr.co.hanip.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(UserJoinReq req);
    UserLoginRes findByLoginId(UserLoginReq req);
    String findRoleByUserId(int userId);
    UserGetRes findByUserId(int userId);
    String findPasswordByUserId(int userId);
    int update(UserPutDto dto);
    int updatePassword(UserPatchPasswordDto dto);
}
