package shop.wesellbuy.secondproject.repository.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * search Member id by condition dto
 * writer : 이호진
 * init : 2023.01.17
 * updated by writer :
 * update :
 * description : 회원의 아이디를 찾을 때 필요한 dto
 */
@Getter
@AllArgsConstructor
public class MemberSearchIdCond {

    private String name; // 회원 이름
    private String selfPhone; // 회원 휴대폰번호
    private String email; // 회원 이메일

}
