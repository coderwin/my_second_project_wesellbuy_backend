package shop.wesellbuy.secondproject.repository.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * search Member password by condition dto
 * writer : 이호진
 * init : 2023.01.17
 * updated by writer :
 * update :
 * description : 회원의 비밀번호를 찾을 때 필요한 dto
 */
@Getter
@AllArgsConstructor
public class MemberSearchPwdCond {

    private String id; // 회원 아이디
    private String selfPhone; // 회원 휴대폰번호
    private String email; // 회원 이메일
}
