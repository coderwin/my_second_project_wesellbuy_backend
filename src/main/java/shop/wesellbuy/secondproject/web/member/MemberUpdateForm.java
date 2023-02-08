package shop.wesellbuy.secondproject.web.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import shop.wesellbuy.secondproject.util.ValidationOfPattern;

/**
 * 회원 수정 정보 form dto
 * writer : 이호진
 * init : 2023.01.26
 * updated by writer : 이호진
 * update : 2023.02.08
 * description : 클라이언트가 보내온 회원 수정 정보를 담아둔다.
 *
 * update : > 비밀버호 확인 filed 추가
 *          > 생성자 추가
 */
@Getter @Setter
@AllArgsConstructor
public class MemberUpdateForm {

    private String pwd; // 비밀번호
    private String pwdConfirm; // 비밀번호 확인
    private String email; // 이메일
    private String selfPhone; // 휴대전화(필수)
    private String homePhone; // 집전화(선택)
    @NotBlank(message = "국적을 선택해주세요")
    private String country; // 나라 이름
    @NotBlank(message = "지역을 선택해주세요")
    private String city; // 지역 이름
    @NotBlank(message = "동/거리명을 입력해주세요")
    private String street; // 동
    @NotBlank(message = "상세주소를 입력해주세요")
    private String detail; // 상세주소
    @NotBlank(message = "우편번호를 입력해주세요")

    private String zipcode; // 우편보호

    private MultipartFile file; // 회원 이미지

    // 생성자
    public MemberUpdateForm(String pwd, String email, String selfPhone, String homePhone, String country, String city, String street, String detail, String zipcode, MultipartFile file) {
        this.pwd = pwd;
        this.email = email;
        this.selfPhone = selfPhone;
        this.homePhone = homePhone;
        this.country = country;
        this.city = city;
        this.street = street;
        this.detail = detail;
        this.zipcode = zipcode;
        this.file = file;
    }

    // ** 비즈니스 메서드 ** //
    /**
     * writer : 이호진
     * init : 2023.02.08
     * updated by writer :
     * update :
     * description : 회원 정보 수정 value 입력 오류 검사
     */
    public void validateJoinValues(BindingResult bindingResult) {

        // 비밀번호 오류
        String patternPwd = "^(?=.*[a-z])(?=.*\\d)(?=.*[?<>~!@#$%^&*_+-])[a-z\\d?<>~!@#$%^&*_+-]{8,21}$";
        ValidationOfPattern.validateValues(patternPwd, this.getPwd(), bindingResult, "pwd", "failed", null);

        // 비밀번호 확인 오류
        String pwd = this.getPwd();
        String pwdConfirm = this.getPwdConfirm();
        if(StringUtils.hasText(pwd) && StringUtils.hasText(pwdConfirm)) {
            if(!pwd.equals(pwdConfirm)) {
                bindingResult.rejectValue("pwdConfirm", "failed", null);
            }
        }

        // 이메일 오류
        String patternEmail = "^\\w+@[a-zA-Z\\d]+\\.[a-zA-Z\\d]+(\\.[a-zA-Z\\d]+)?$";
        ValidationOfPattern.validateValues(patternEmail, this.getEmail(), bindingResult, "memberEmail", "failed", null);

        // 휴대전화 오류
        String patternSelfPhone = "^01(0|[6-9])\\d{4}\\d{4}$";
        ValidationOfPattern.validateValues(patternSelfPhone, this.getSelfPhone(), bindingResult, "selfPhone", "failed", null);

        // 선택 사항으로 만들기
        // 집전화 오류
//        String patternPhone2 = "^0(2|[3-6][1-5])\\d{3,4}\\d{4}$";
//        ValidationOfPattern.validateValues(patternPhone2, this.getPhone2(), bindingResult, "phone2", "failed", null);

        // 파일 확장자 조사
        String patternFile = ".*(?<=\\.(jpg|JPG|png|PNG|jpeg|JPEG|gif|GIF))";
        ValidationOfPattern.validateValues(patternFile, this.getFile().getOriginalFilename(), bindingResult, "file", "falied", null);
    }
}
