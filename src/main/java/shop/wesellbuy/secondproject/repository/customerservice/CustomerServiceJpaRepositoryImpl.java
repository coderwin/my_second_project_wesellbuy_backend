package shop.wesellbuy.secondproject.repository.customerservice;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import shop.wesellbuy.secondproject.domain.CustomerService;
import shop.wesellbuy.secondproject.domain.QCustomerService;

import java.util.List;

import static shop.wesellbuy.secondproject.domain.QCustomerService.customerService;
import static shop.wesellbuy.secondproject.domain.QMember.member;

/**
 * CustomerServiceJpaRepositoryCustom 구현
 * writer : 이호진
 * init : 2023.01.16
 * updated by writer :
 * update :
 * description : Admin에서 사용하는 MemberJpaRepository 구현 모음 + 최적화 사용(fetch)
 */
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceJpaRepositoryImpl implements CustomerServiceJpaRepositoryCustom{

    private final JPAQueryFactory query;

    /**
     * writer : 이호진
     * init : 2023.01.17
     * updated by writer :
     * update :
     * description : 모든 고객지원 게시글 찾기 + fetchjoin
     */
    @Override
    public Page<CustomerService> findAllInfo(CustomerServiceSearchCond customerServiceSearchCond, Pageable pageable) {

        // list
        List<CustomerService> result = query
                .select(customerService)
                .from(customerService)
                .join(customerService.member, member).fetchJoin()
                .where(
                        customerServiceIdEq(customerServiceSearchCond.getMemberId()),
                        customerServiceReportedIdEq(customerServiceSearchCond.getReportedId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(customerService.num.desc())
                .fetch();

        // totalCount
        Long totalCount = query
                .select(customerService.count())
                .from(customerService)
                .where(
                        customerServiceIdEq(customerServiceSearchCond.getMemberId()),
                        customerServiceReportedIdEq(customerServiceSearchCond.getReportedId())
                )
                .fetchOne();

        return new PageImpl<>(result, pageable, totalCount);
    }

    /**
     * writer : 이호진
     * init : 2023.01.17
     * updated by writer :
     * update :
     * description : 고객지원 정보 검색 조건 eq by 회원아이디(신고당한 아이디)
     */
    private BooleanExpression customerServiceReportedIdEq(String reportedId) {
        return StringUtils.hasText(reportedId) ? customerService.reportedId.eq(reportedId) : null;
    }

    /**
     * writer : 이호진
     * init : 2023.01.17
     * updated by writer :
     * update :
     * description : 고객지원 정보 검색 조건 eq by 회원아이디(신고한 아이디)
     */
    private BooleanExpression customerServiceIdEq(String memberId) {
        return StringUtils.hasText(memberId) ? customerService.member.id.eq(memberId) : null;
    }
}
