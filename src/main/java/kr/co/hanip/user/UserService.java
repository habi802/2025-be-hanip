package kr.co.hanip.user;

import kr.co.hanip.store.StoreMapper;
import kr.co.hanip.store.model.StoreAndJoinReq;
import kr.co.hanip.store.model.StorePostDto;
import kr.co.hanip.store.model.StorePostReq;
import kr.co.hanip.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final StoreMapper storeMapper;

    @Transactional
    int join(StoreAndJoinReq req) {
        StorePostReq storeReq = req.getStorePostReq();
        UserJoinReq userReq = req.getUserJoinReq();
        String hashedPw = BCrypt.hashpw(userReq.getLoginPw(), BCrypt.gensalt());

        UserJoinDto dto = UserJoinDto.builder()
                .name(req.getUserJoinReq().getName())
                .loginId(req.getUserJoinReq().getLoginId())
                .loginPw(hashedPw)
                .address(req.getUserJoinReq().getAddress())
                .phone(req.getUserJoinReq().getPhone())
                .email(req.getUserJoinReq().getEmail())
                .imagePath(req.getUserJoinReq().getImagePath())
                .role(req.getUserJoinReq().getRole())
                .build();

        log.info("user joinReq:{}", dto);
        userMapper.save(dto);

        StorePostDto storePostDto = StorePostDto.builder()
                .userId(dto.getUserId())
                .category(storeReq.getCategory())
                .name(storeReq.getName())
                .comment(storeReq.getComment())
                .businessNumber(storeReq.getBusinessNumber())
                .licensePath(storeReq.getLicensePath())
                .address(storeReq.getAddress())
                .tel(storeReq.getTel())
                .ownerName(storeReq.getOwnerName())
                .build();
        return storeMapper.save(storePostDto);
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
