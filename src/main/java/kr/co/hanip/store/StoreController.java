package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.store.model.StorePostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 가게 등록
    @PostMapping
    public ResponseEntity<?> saveStore(@RequestBody StorePostReq req, HttpServletRequest httpReq) {
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, "OWNER");
        log.info("storeSaveReq: {}", req);
        int result = storeService.saveStore(req, logginedUserId);
        return ResponseEntity.ok(result);
    }
}
