package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.store.model.*;
import kr.co.hanip.user.etc.UserConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> saveStore(@RequestBody StorePostReq req, HttpServletRequest httpReq) {
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        log.info("storeSaveDto: {}", req);
        int result = storeService.saveStore(req, logginedUserId);
        return ResponseEntity.ok(result);
    }

    // 가게 조회 (GET)
    @GetMapping
    public ResponseEntity<ResultResponse<List<StoreGetListRes>>> getStoreList(@ModelAttribute StoreGetListReq req) {
        log.info("getStoreListReq: {}", req);
        List<StoreGetListRes> storeListRes = storeService.findAllStore(req);
        log.info("getStoreListRes: {}", storeListRes);
        return ResponseEntity.ok(ResultResponse.success(storeListRes));
    }

    // 가게 상세 조회 (GET)
    @GetMapping("{storeId}")
    public ResponseEntity<ResultResponse<StoreGetRes>> getStoreDetail(@PathVariable int storeId) {
        log.info("getStoreDetailStoreId: {}", storeId);
        StoreGetRes storeGetRes = storeService.findStore(storeId);
        log.info("getStoreDetailGetRes: {}", storeGetRes);
        if (storeGetRes != null) {
            return ResponseEntity.ok(ResultResponse.success(storeGetRes));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResultResponse.fail(404, "가게를 찾을 수 없습니다."));
        }
    }

    // 가게 정보 수정 (PUT)
    @PutMapping
    public ResponseEntity<ResultResponse<Integer>> modifyStore(@RequestBody StorePutReq req, HttpServletRequest httpReq) {
        log.info("modifyReq: {}", req);
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int result = storeService.modifyStore(req, logginedUserId);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "비밀번호가 일치하지 않습니다."));
        }
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    // 가게 삭제 (DELETE)
    @DeleteMapping
    public ResponseEntity<ResultResponse<Integer>> deleteStore(@RequestBody StoreDeleteReq req, HttpServletRequest httpReq) {
        log.info("deleteReq : {}", req);
        int logginedUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        req.setStoreId(req.getStoreId());
        int result = storeService.removeStore(req, logginedUserId);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401,"비밀번호가 일치하지 않습니다."));
        }
        return ResponseEntity.ok(ResultResponse.success(result));
    }
}
