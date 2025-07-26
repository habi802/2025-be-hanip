package kr.co.hanip.customer;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.customer.etc.CustomerConstants;
import kr.co.hanip.customer.etc.CustomerJoinConstants;
import kr.co.hanip.customer.model.CustomerJoinReq;
import kr.co.hanip.customer.model.CustomerLoginReq;
import kr.co.hanip.customer.model.CustomerLoginRes;
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

    @PostMapping("/login")
    public ResponseEntity<ResultResponse<CustomerLoginRes>> login(@RequestBody CustomerLoginReq req, HttpServletRequest httpReq) {
        CustomerLoginRes result = customerService.login(req);
        if (result == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResultResponse.fail(401, "아이디나 비밀번호가 올바르지 않습니다."));
        }

        HttpUtils.setSession(httpReq, CustomerConstants.LOGGED_IN_CUSTOMER_ID, result.getId());

        return ResponseEntity.ok(ResultResponse.success(result));
    }
}
