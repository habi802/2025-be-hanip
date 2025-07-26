package kr.co.hanip.customer;

import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.customer.etc.CustomerJoinConstants;
import kr.co.hanip.customer.model.CustomerJoinReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    ResponseEntity<ResultResponse<?>> join(@RequestBody CustomerJoinReq req) {
        Integer result = customerService.join(req);

        if (CustomerJoinConstants.DUPLICATE_ID.equals(result)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResultResponse.fail(400, "이미 등록된 아이디입니다."));
        } else if (CustomerJoinConstants.PASSWORD_MISMATCH.equals(result)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResultResponse.fail(400, "비밀번호가 일치하지 않습니다."));
        }

        return result == 0
                ? ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResultResponse.fail(400, "등록 실패"))
                : ResponseEntity.ok(ResultResponse.success(result));
    }

    @GetMapping("/check-id")
    public ResponseEntity<ResultResponse<?>> checkLoginId(@RequestParam String loginId) {
        Integer result = customerService.checkLoginId(loginId);

        return result != null
                ? ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResultResponse.fail(400, "이미 등록된 아이디입니다."))
                : ResponseEntity.ok(ResultResponse.success(result));
    }
}
