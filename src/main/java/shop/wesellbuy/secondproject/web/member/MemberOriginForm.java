package shop.wesellbuy.secondproject.web.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import shop.wesellbuy.secondproject.domain.member.SelfPicture;

@Getter @Setter
@AllArgsConstructor
public class MemberOriginForm {

    private String name; // 이름
    private String id; // 아이디
    private String email; // 이메일
    private String selfPhone; // 휴대전화(필수)
    private String homePhone; // 집전화(선택)
    private String country; // 나라 이름
    private String city; // 지역 이름
    private String street; // 동
    private String detail; // 상세주소
    private String zipcode; // 우편보호

    private MultipartFile file; // 회원 이미지

    // ** 비즈니스 로직 ** //
    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : MemberOriginForm을 MemberForm으로 변경
     */
    public MemberForm changeAsMemberForm(SelfPicture selfPicture) {

        //
        MemberForm memberForm = new MemberForm(name, id, email, selfPhone, homePhone, country, city, street, detail, zipcode, selfPicture);

        return memberForm;
    }


}
