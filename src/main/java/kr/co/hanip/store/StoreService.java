package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.store.model.StorePostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreMapper storeMapper;

    public int saveStore(StorePostReq req, int userId) {
        int result = storeMapper.save(req);

        return 1;
    }

}
