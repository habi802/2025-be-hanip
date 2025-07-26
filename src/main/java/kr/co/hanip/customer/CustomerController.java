package kr.co.hanip.customer;

import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.customer.model.CustomerJoinReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    ResponseEntity<ResultResponse<?>> join(@RequestBody CustomerJoinReq req) {
        Integer result = customerService.join(req);

        if (result == null) {
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
}
