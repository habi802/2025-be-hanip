package kr.co.hanip.user;

import kr.co.hanip.user.model.UserGetRes;
import kr.co.hanip.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    int join(UserPostReq req) {
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());

        UserPostReq joinReq = new UserPostReq(req.getName(), req.getLoginId(), hashedPw, req.getAddress(), req.getPhone(), req.getEmail(), req.getImagePath(), req.getRole());

        log.info("user joinReq:{}", joinReq);
        return userMapper.save(joinReq);
    }

    UserGetRes find(int loggedInUserId) {
        return userMapper.findByUserId(loggedInUserId);
    }
}
