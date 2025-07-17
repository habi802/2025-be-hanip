package kr.co.hanip.user;

import kr.co.hanip.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    int join(UserJoinReq req) {
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());

        UserJoinReq joinReq = UserJoinReq.builder()
                .name(req.getName())
                .loginId(req.getLoginId())
                .loginPw(hashedPw)
                .address(req.getAddress())
                .phone(req.getPhone())
                .email(req.getEmail())
                .imagePath(req.getImagePath())
                .role(req.getRole())
                .build();

        log.info("user joinReq:{}", joinReq);
        return userMapper.save(joinReq);
    }

    UserLoginRes login(UserLoginReq req) {
        UserLoginRes res = userMapper.findByLoginId(req);

        if (res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null;
        }

        return res;
    }

    UserGetRes find(int loggedInUserId) {
        return userMapper.findByUserId(loggedInUserId);
    }

    Integer update(int loggedInUserId, UserUpdateReq req) {
        String currentPw = userMapper.findPasswordByUserId(loggedInUserId);

        if (currentPw == null || !BCrypt.checkpw(req.getLoginPw(), currentPw)) {
            return null;
        }

        String hashedNewPw = BCrypt.hashpw(req.getNewLoginPw(), BCrypt.gensalt());

        UserUpdateDto dto = UserUpdateDto.builder()
                .userId(loggedInUserId)
                .newLoginPw(hashedNewPw)
                .name(req.getName())
                .address(req.getAddress())
                .phone(req.getPhone())
                .email(req.getEmail())
                .imagePath(req.getImagePath())
                .build();

        return userMapper.update(dto);
    }
}