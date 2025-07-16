package kr.co.hanip.user;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.user.model.UserGetRes;
import kr.co.hanip.user.model.UserPostReq;
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
    public ResponseEntity<?> join(@RequestBody UserPostReq req) {
        //log.info("user-post-req: {}", req);
        int result = userService.join(req);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> find(HttpServletRequest httpReq) {
        int loggedInUserId = 1;
        UserGetRes result = userService.find(loggedInUserId);
        return ResponseEntity.ok(result);
    }
}
