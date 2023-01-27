package shop.wesellbuy.secondproject.web.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@AllArgsConstructor
public class MemberUpdateForm {

    private String pwd; // 비밀번호
    private String email; // 이메일
    private String selfPhone; // 휴대전화(필수)
    private String homePhone; // 집전화(선택)
    private String country; // 나라 이름
    private String city; // 지역 이름
    private String street; // 동
    private String detail; // 상세주소
    private String zipcode; // 우편보호

    private MultipartFile file; // 회원 이미지

}
