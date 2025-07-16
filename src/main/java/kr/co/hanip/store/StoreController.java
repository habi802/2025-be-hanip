package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.store.model.*;
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
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, "USER_ID");
        log.info("storeSaveDto: {}", dto);
        int result = storeService.saveStore(dto, logginedUserId);
        return ResponseEntity.ok(result);
    }

    // 가게 조회 (GET)
    @GetMapping
    public ResponseEntity<?> getStoreList(@ModelAttribute StoreGetListReq req) {
        log.info("getStoreListReq: {}", req);
        List<StoreGetListRes> storeListRes = storeService.findAllStore(req);
        log.info("getStoreListRes: {}", storeListRes);
        return ResponseEntity.ok(storeListRes);
    }

    // 가게 상세 조회 (GET)
    @GetMapping("{storeId}")
    public ResponseEntity<?> getStoreDetail(@PathVariable int storeId) {
        log.info("getStoreDetailStoreId: {}", storeId);
        StoreGetRes storeGetRes = storeService.findStore(storeId);
        log.info("getStoreDetailGetRes: {}", storeGetRes);
        return ResponseEntity.ok(storeGetRes);
    }

    // 가게 정보 수정 (PUT)
    @PutMapping
    public ResponseEntity<?> modifyStore(@RequestBody StorePutReq req, HttpServletRequest httpReq) {
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, "USER_ID");
        log.info("modifyReq: {}", req);
        int result = storeService.modifyStore(req, logginedUserId);
        return ResponseEntity.ok(result);
    }

    // 가게 삭제 (DELETE)
    @DeleteMapping("{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable int storeId, HttpServletRequest httpReq) {
        log.info("deleteStoreId: {}", storeId);
        int logginedUserId = 7;
        // (int) HttpUtils.getSessionValue(httpReq, "USER_ID");
        StoreDeleteReq storeDeleteReq = new StoreDeleteReq(storeId, logginedUserId);
        int result = storeService.removeStore(storeDeleteReq);
        return ResponseEntity.ok(result);
    }
}
