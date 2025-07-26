package kr.co.hanip.customer.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomerJoinReq {
    @NotBlank(message = "아이디를 입력해주세요..")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상, 20자 이하로 입력해주세요.")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, max = 20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요.")
    private String loginPw;
    @NotBlank(message = "비밀번호를 다시 한 번 입력해주세요.")
    private String loginPwCheck;
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 1, max = 20, message = "이름은 1자 이상, 20자 이하로 입력해주세요.")
    private String name;
    @NotBlank(message = "우편 번호를 입력해주세요.")
    @Size(min = 1, max = 20, message = "우편번호는 6자 이하로 입력해주세요.")
    private String postcode;
    @NotBlank(message = "주소를 입력해주세요.")
    private String address;
    @NotBlank(message = "상세 주소를 입력해주세요.")
    private String addressDetail;
    @Pattern(regexp = "^$|^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "일반전화 형식이 올바르지 않습니다.")
    private String tel;
    @NotBlank(message = "전화 번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phone;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private String imagePath;
    private String imageName;
}
