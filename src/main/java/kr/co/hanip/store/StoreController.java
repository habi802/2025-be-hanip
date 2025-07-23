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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

//    // 가게 등록 (POST)
//    @PostMapping
//    public ResponseEntity<?> saveStore(@RequestBody StorePostReq req, HttpServletRequest httpReq) {
//        int result = storeService.saveStore(req);
//        return ResponseEntity.ok(result);
//    }

    // 가게 조회 (GET)
    @GetMapping
    public ResponseEntity<ResultResponse<List<StoreGetListRes>>> findAllStore(@ModelAttribute StoreGetListReq req) {
        // log.info("getStoreListReq: {}", req);
        List<StoreGetListRes> storeListRes = storeService.findAllStore(req);
        // log.info("getStoreListRes: {}", storeListRes);
        return ResponseEntity.ok(ResultResponse.success(storeListRes));
    }

    // 가게 상세 조회 (GET)
    @GetMapping("/{storeId}")
    public ResponseEntity<ResultResponse<StoreGetRes>> findStore(@PathVariable int storeId) {
        // log.info("getStoreDetailStoreId: {}", storeId);
        StoreGetRes storeGetRes = storeService.findStore(storeId);
        // log.info("getStoreDetailGetRes: {}", storeGetRes);
        if (storeGetRes == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResultResponse.fail(404, "가게를 찾을 수 없습니다."));
        } else {
            return ResponseEntity.ok(ResultResponse.success(storeGetRes));
        }
    }

    // 사장 가게 상세 조회 (GET)
    @GetMapping("/owner")
    public ResponseEntity<ResultResponse<StoreGetDto>> findStore(HttpServletRequest httpReq) {
        // log.info("getStoreDetailStoreId: {}", storeId);
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        StoreGetDto storeGetRes = storeService.findByUserId(loggedInUserId);
        // log.info("getStoreDetailGetRes: {}", storeGetRes);
        if (storeGetRes == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResultResponse.fail(404, "가게를 찾을 수 없습니다."));
        } else {
            return ResponseEntity.ok(ResultResponse.success(storeGetRes));
        }
    }

    // 가게 정보 수정 (PUT)
    @PutMapping
    public ResponseEntity<ResultResponse<Integer>> updateStore(@RequestPart(required = false) MultipartFile img,
                                                               @RequestPart StorePutReq data,HttpServletRequest httpReq) {
        log.info("img: {}", img);
        log.info("data: {}", data);
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int result = storeService.modifyStore(img, data, loggedInUserId);
        if (result == 0) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "비밀번호가 올바르지 않습니다."));
        }
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    // 가게 활성화 수정 (PATCH)
    @PatchMapping("/{storeId}")
    public ResponseEntity<ResultResponse<Integer>> updateStoreActive(@PathVariable int storeId, HttpServletRequest httpReq) {
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int result = storeService.modifyStoreActive(storeId, loggedInUserId);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    // 가게 삭제 (DELETE)
    @DeleteMapping
    public ResponseEntity<ResultResponse<Integer>> deleteStore(@RequestBody StoreDeleteReq req, HttpServletRequest httpReq) {
        // log.info("deleteReq : {}", req);
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int result = storeService.removeStore(req, loggedInUserId);
        if (result == 0) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401,"비밀번호가 올바르지 않습니다."));
        }
        return ResponseEntity.ok(ResultResponse.success(result));
    }
}
