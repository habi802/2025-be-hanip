package kr.co.hanip.store;

import kr.co.hanip.store.model.StoreGetRes;
import kr.co.hanip.store.model.StoreListReq;
import kr.co.hanip.store.model.StoreListRes;
import kr.co.hanip.store.model.StorePostDto;
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

    public List<StoreListRes> findAllStore(StoreListReq req) {
        return storeMapper.findAllStore(req);
    }

    public StoreGetRes findStoreById(int storeId) {
        return storeMapper.findStoreById(storeId);
    }

}
