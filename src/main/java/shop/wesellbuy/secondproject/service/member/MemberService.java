package shop.wesellbuy.secondproject.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.wesellbuy.secondproject.repository.member.MemberSearchCond;
import shop.wesellbuy.secondproject.web.member.MemberDetailForm;
import shop.wesellbuy.secondproject.web.member.MemberOriginForm;
import shop.wesellbuy.secondproject.web.member.MemberUpdateForm;

import java.io.IOException;

/**
 * Member Service
 * writer : 이호진
 * init : 2023.01.26
 * updated by writer :
 * update :
 * description : Member Service 메소드 모음
 */
public interface MemberService {

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 가입
     *
     * @return
     */
    int join(MemberOriginForm memberOriginFormForm) throws IOException;

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 정보 수정
     */
    void update(MemberUpdateForm memberUpdateForm, Integer num) throws IOException;

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 탈퇴
     *               -> status 변경
     */
    void withdrawal(int num);

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 회원 정보 상세보기
     */
    MemberDetailForm watchDetail(int num);

//    ------------------------------methods using admin --------------------------------

    /**
     * writer : 이호진
     * init : 2023.01.26
     * updated by writer :
     * update :
     * description : 모든 회원 정보 가져오기 + 검색
     */
    Page<MemberDetailForm> selectList(MemberSearchCond memberSearchCond, Pageable pageable);
}
