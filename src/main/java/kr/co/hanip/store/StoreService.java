package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.store.model.*;
import kr.co.hanip.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreMapper storeMapper;
    private final UserMapper userMapper;

//    public int saveStore(StorePostReq req) {
//        StorePostDto storePostDto = StorePostDto.builder()
//                .userId()
//                .build();
//        int result = storeMapper.save(storePostDto);
//    }

    public List<StoreGetListRes> findAllStore(StoreGetListReq req) {
        return storeMapper.findAllStore(req);
    }

    public StoreGetRes findStore(int storeId) {
        return storeMapper.findStoreByStoreId(storeId);
    }

    public int modifyStore(StorePutReq req, int userId) {
        String encodePw = userMapper.findPasswordByUserId(userId);
        if (!BCrypt.checkpw(req.getPassword(), encodePw)) {
            return 0;
        } else {
             StorePutDto storePutDto = StorePutDto.builder()
                    .userId(userId)
                    .storeId(req.getStoreId())
                    .category(req.getCategory())
                    .name(req.getName())
                    .comment(req.getComment())
                    .businessNumber(req.getBusinessNumber())
                    .licensePath(req.getLicensePath())
                    .address(req.getAddress())
                    .tel(req.getTel())
                    .ownerName(req.getOwnerName())
                     .password(req.getPassword())
                    .build();

            return storeMapper.modifyStoreByUserId(storePutDto);
        }
    }

    public int modifyStoreActive(int storeId, int userId) {
        return storeMapper.modifyStoreActiveByStoreIdAndUserId(storeId, userId);
    }


    public int removeStore(StoreDeleteReq req, int userId) {
        String encodePw = userMapper.findPasswordByUserId(userId);
        StoreDeleteDto storeDeleteDto = new StoreDeleteDto(req.getStoreId(), userId, req.getPassword());
        if (!BCrypt.checkpw(req.getPassword(), encodePw)) {
            return 0;
        } else {
            return storeMapper.deleteStoreByStoreIdAndUserId(storeDeleteDto);
        }
    }

}
