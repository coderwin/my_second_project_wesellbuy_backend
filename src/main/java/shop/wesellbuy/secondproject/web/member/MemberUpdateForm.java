package shop.wesellbuy.secondproject.web.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 회원 수정 정보 form dto
 * writer : 이호진
 * init : 2023.01.26
 * updated by writer :
 * update :
 * description : 클라이언트가 보내온 회원 수정 정보를 담아둔다.
 */
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
