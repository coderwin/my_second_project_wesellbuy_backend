package shop.wesellbuy.secondproject.web.member.login;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원 아이디 찾기 dto
 * writer : 이호진
 * init : 2023.01.26
 * updated by writer :
 * update :
 * description : 회원 아이디 찾기 페이지의 form
 */
@Getter @Setter
public class LoginSearchIdForm {
    private String name; // 이름
    private String email; // 이메일
    private String selfPone; // 휴대전화 번호

}
