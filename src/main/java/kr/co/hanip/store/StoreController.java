package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.store.model.StoreGetRes;
import kr.co.hanip.store.model.StoreListReq;
import kr.co.hanip.store.model.StoreListRes;
import kr.co.hanip.store.model.StorePostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 가게 등록 (POST)
    @PostMapping
    public ResponseEntity<?> saveStore(@RequestBody StorePostDto dto, HttpServletRequest httpReq) {
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, "OWNER");
        log.info("storeSaveDto: {}", dto);
        int result = storeService.saveStore(dto, logginedUserId);
        return ResponseEntity.ok(result);
    }

    // 가게 조회 (GET)
    @GetMapping
    public ResponseEntity<?> getStoreList(@ModelAttribute StoreListReq req) {
        log.info("getStoreListReq: {}", req);
        List<StoreListRes> storeListRes = storeService.findAllStore(req);
        log.info("getStoreListRes: {}", storeListRes);
        return ResponseEntity.ok(storeListRes);
    }

    // 가게 상세 조회 (GET)
    @GetMapping
    public ResponseEntity<?> getStoreDetail(@PathVariable int storeId) {
        log.info("getStoreDetailStoreId: {}", storeId);
        StoreGetRes storeGetRes = storeService.findStoreById(storeId);
        log.info("getStoreDetailGetRes: {}", storeGetRes);
        return ResponseEntity.ok(storeGetRes);
    }

    // 가게 정보 수정 (PUT)

    // 가게 삭제 (DELETE)
}
