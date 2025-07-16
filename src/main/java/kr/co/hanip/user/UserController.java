package kr.co.hanip.user;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinReq req) {
        //log.info("user-post-req: {}", req);
        int result = userService.join(req);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReq req, HttpServletRequest httpReq) {
        UserLoginRes result = userService.login(req);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }

        HttpUtils.setSession(httpReq, "LoggedInUserId", result.getId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest httpReq) {
        int result = (int) HttpUtils.getSessionValue(httpReq, "LoggedInUserId");
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> find(HttpServletRequest httpReq) {
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, "LoggedInUserId");
        UserGetRes result = userService.find(loggedInUserId);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserUpdateReq req, HttpServletRequest httpReq) {
        int loggedInUserId = (int) HttpUtils.getSessionValue(httpReq, "LoggedInUserId");
        Integer result = userService.update(loggedInUserId, req);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
