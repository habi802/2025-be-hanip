package kr.co.hanip.user;

import kr.co.hanip.store.StoreMapper;
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
    int join(UserJoinReq req) {
        int result = 0;

        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());

        UserJoinReq joinReq = UserJoinReq.builder()
                .name(req.getName())
                .loginId(req.getLoginId())
                .loginPw(hashedPw)
                .postcode(req.getPostcode())
                .address(req.getAddress())
                .addressDetail(req.getAddressDetail())
                .phone(req.getPhone())
                .email(req.getEmail())
                .imagePath(req.getImagePath())
                .role(req.getRole())
                .owner(req.getOwner())
                .build();

        //log.info("user joinReq:{}", joinReq);
        result += userMapper.save(joinReq);

        if (joinReq.getOwner() != null) {
            StorePostReq storeReq = StorePostReq.builder()
                    .userId(joinReq.getId())
                    .category(joinReq.getOwner().getCategory())
                    .name(joinReq.getOwner().getName())
                    .comment(joinReq.getOwner().getComment())
                    .businessNumber(joinReq.getOwner().getBusinessNumber())
                    .licensePath("001.jpg")
                    .postcode(joinReq.getPostcode())
                    .address(joinReq.getAddress())
                    .addressDetail(joinReq.getAddressDetail())
                    .tel(joinReq.getOwner().getTel())
                    .ownerName(joinReq.getName())
                    .build();

            result += storeMapper.save(storeReq);
        }

        return result;
    }

    UserLoginRes login(UserLoginReq req) {
        UserLoginRes res = userMapper.findByLoginId(req);

        if (res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null;
        }

        Integer storeId = storeMapper.findStoreIdByUserId(res.getId());
        res.setStoreId(storeId == null ? 0 : storeId);
        String role = userMapper.findRoleByUserId(res.getId());
        res.setRole(role);

        return res;
    }

    UserGetRes find(int loggedInUserId) {
        return userMapper.findByUserId(loggedInUserId);
    }

    Integer update(int loggedInUserId, UserPutReq req) {
        String currentPw = userMapper.findPasswordByUserId(loggedInUserId);

        if (currentPw == null || !BCrypt.checkpw(req.getLoginPw(), currentPw)) {
            return null;
        }

        UserPutDto dto = UserPutDto.builder()
                .userId(loggedInUserId)
                .name(req.getName())
                .postcode(req.getPostcode())
                .address(req.getAddress())
                .addressDetail(req.getAddressDetail())
                .phone(req.getPhone())
                .email(req.getEmail())
                .imagePath(req.getImagePath())
                .build();

        return userMapper.update(dto);
    }

    Integer updatePassword(int loggedInUserId, UserPatchPasswordReq req) {
        String currentPw = userMapper.findPasswordByUserId(loggedInUserId);

        if (currentPw == null || !BCrypt.checkpw(req.getLoginPw(), currentPw)) {
            return null;
        }

        String hashedNewPw = BCrypt.hashpw(req.getNewLoginPw(), BCrypt.gensalt());

        UserPatchPasswordDto dto = UserPatchPasswordDto.builder()
                .userId(loggedInUserId)
                .newLoginPw(hashedNewPw)
                .build();

        return userMapper.updatePassword(dto);
    }
}
