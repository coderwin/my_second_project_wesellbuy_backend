package shop.wesellbuy.secondproject.repository.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.util.LocalDateParser;

import java.util.List;

import static shop.wesellbuy.secondproject.domain.QMember.member;

/**
 * Admin에서 사용하는 MemberJpaRepository 구현
 * writer : 이호진
 * init : 2023.01.16
 * updated by writer :
 * update :
 * description : Admin에서 사용하는 MemberJpaRepository 구현 모음 + 최적화 사용(fetch)
 */
@RequiredArgsConstructor
@Slf4j
public class MemberJpaRepositoryImpl implements MemberJpaRepositoryCustom{

    private final JPAQueryFactory query; // qureyDsl 사용 위한 필드

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : 전체 회원정보 찾기 + paging 구현 admin에서 사용
     */
    @Override
    public Page<Member> findAllInfo(MemberSearchCond memberSearchCond, Pageable pageable) {

        // 모든 회원 정보 query 보내기
        List<Member> result = query
                .selectFrom(member)
                .where(memberIdLike(memberSearchCond.getId()),
                        memberCountryLike(memberSearchCond.getCountry()),
                        memberCityLike(memberSearchCond.getCity()),
                        memberCreateDateBetween(memberSearchCond.getCreateDate()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(member.num.desc())
                .fetch();

//        // 모든 회원 정보 count 가져오기 v1 using PageableExecutionUtils
//        JPAQuery<Long> countQuery = query
//                .select(member.count())
//                .from(member)
//                .where();

        // 모든 회원 정보 count 가져오기 v2 using PageImpl
        Long totalCount = query
                .select(member.count())
                .from(member)
                .where(
                        memberIdLike(memberSearchCond.getId()),
                        memberCountryLike(memberSearchCond.getCountry()),
                        memberCityLike(memberSearchCond.getCity()),
                        memberCreateDateBetween(memberSearchCond.getCreateDate())
                )
                .fetchOne();

//        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne());
//        return PageableExecutionUtils.getPage(result, pageable, () -> countQuery.fetchOne());
        return new PageImpl(result, pageable, totalCount);
    }

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : 회원 정보 검색 조건 between by createDate
     */
    private BooleanExpression memberCreateDateBetween(String createDate) {
        if(StringUtils.hasText(createDate)) {
            // String date를 LocalDate로 change
            LocalDateParser localDateParser = new LocalDateParser(createDate);

            return member.createdDate.between(localDateParser.startDate(), localDateParser.endDate());
        }
        return null;

    }

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : 회원 정보 검색 조건 like by city
     */
    private BooleanExpression memberCityLike(String city) {
        if(StringUtils.hasText(city)) {
            return member.address.city.like("%" + city + "%");
        }
        return null;
    }

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : 회원 정보 검색 조건 like by country
     */
    private BooleanExpression memberCountryLike(String country) {
        if(StringUtils.hasText(country)) {
            return member.address.country.like("%" + country + "%");
        }
        return null;
    }

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : 회원 정보 검색 조건 like by id
     */
    private BooleanExpression memberIdLike(String id) {
        if(StringUtils.hasText(id)) {
            return member.id.like("%" + id + "%");
        }
        return null;
    }
}
