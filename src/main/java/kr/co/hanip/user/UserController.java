package kr.co.hanip.user;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.user.etc.UserConstants;
import kr.co.hanip.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ResultResponse<Integer>> join(@RequestBody UserJoinReq req) {
        //log.info("user-post-req: {}", req);
        int result = userService.join(req);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @PostMapping("/login")
    public ResponseEntity<ResultResponse<UserLoginRes>> login(@RequestBody UserLoginReq req, HttpServletRequest httpReq) {
        UserLoginRes result = userService.login(req);
        if (result == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "아이디나 비밀번호가 올바르지 않습니다."));
        }

        HttpUtils.setSession(httpReq, UserConstants.LOGGED_IN_USER_ID, result.getId());

        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @GetMapping("/check")
    public ResponseEntity<ResultResponse<Integer>> check(HttpServletRequest httpReq) {
        int result = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ResultResponse<UserGetRes>> find(HttpServletRequest httpReq) {
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        UserGetRes result = userService.find(loggedInUserId);
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @PutMapping
    public ResponseEntity<ResultResponse<Integer>> update(@RequestBody UserUpdateReq req, HttpServletRequest httpReq) {
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        Integer result = userService.update(loggedInUserId, req);
        if (result == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "비밀번호가 올바르지 않습니다."));
        }
        return ResponseEntity.ok(ResultResponse.success(result));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResultResponse<Integer>> logout(HttpServletRequest httpReq) {
        HttpUtils.removeSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        return ResponseEntity.ok(ResultResponse.success(1));
    }
}
