package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.store.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreMapper storeMapper;

    public int saveStore(StorePostDto req, int userId) {
        int result = storeMapper.save(req);
        return 1;
    }

    public List<StoreGetListRes> findAllStore(StoreGetListReq req) {
        return storeMapper.findAllStore(req);
    }

    public StoreGetRes findStore(int storeId) {
        return storeMapper.findStoreByStoreId(storeId);
    }

    public int modifyStore(StorePutReq req, int userId) {
        StorePutDto storePutDto = StorePutDto.builder()
                .userId(userId)
                .storeId(req.getStoreId())
                .category(req.getCategory())
                .name(req.getName())
                .comment(req.getComment())
                .businessNumber(req.getBusinessNumber())
                .licensePath(req.getLicensePath())
                .address(req.getAddress())
                .build();

        return storeMapper.modifyStoreByUserId(storePutDto);
    }

    public int removeStore(StoreDeleteReq req) {
        return storeMapper.deleteStoreByStoreIdAndUserId(req);
    }

}
