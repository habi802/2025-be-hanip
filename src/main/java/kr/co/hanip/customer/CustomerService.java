package kr.co.hanip.customer;

import kr.co.hanip.customer.model.CustomerJoinReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;

    Integer join(CustomerJoinReq req) {
        if (!req.getLoginPw().equals(req.getLoginPwCheck())) {
            return null;
        }

        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());

        CustomerJoinReq joinReq = CustomerJoinReq.builder()
                .loginId(req.getLoginId())
                .loginPw(hashedPw)
                .name(req.getName())
                .postcode(req.getPostcode())
                .address(req.getAddress())
                .addressDetail(req.getAddressDetail())
                .email(req.getEmail())
                .tel(req.getTel())
                .phone(req.getPhone())
                .imagePath(req.getImagePath())
                .imageName(req.getImageName())
                .build();

        return customerMapper.save(joinReq);
    }
}
