package shop.wesellbuy.secondproject.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.wesellbuy.secondproject.domain.Member;

/**
 * Member Repository by using queryDsl
 * writer : 이호진
 * init : 2023.01.16
 * updated by writer :
 * update :
 * description : querydsl이용한 memberRepository 모음
 */
public interface MemberJpaRepositoryCustom {

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : 전체 회원정보 찾기 + paging
     *              - admin에서 사용
     */
    public Page<Member> findAllInfo(MemberSearchCond memberSearchCond, Pageable pageable);
}
