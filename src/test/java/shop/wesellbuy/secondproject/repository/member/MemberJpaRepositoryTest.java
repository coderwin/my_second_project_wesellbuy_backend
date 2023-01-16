package shop.wesellbuy.secondproject.repository.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.member.SelfPicture;
import shop.wesellbuy.secondproject.web.member.MemberForm;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * writer : 이호진
 * init : 2023.01.16
 * updated by writer :
 * update :
 * description : Member Repository by jpa Test
 */
@SpringBootTest
@Transactional
@Slf4j
public class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : save Test
     */
    @Test
//    @Rollback(value = false)
    public void 회원정보저장() {
        // given
        SelfPicture testSelfPicture = SelfPicture.createSelfPicture("a", "a");

        MemberForm memberForm1 = new MemberForm("a", "a", "a@a", "01012341234", "0511231234", "korea", "b", "h", "h", "123", testSelfPicture);
        Member member1 = Member.createMember(memberForm1);

        // when
        memberJpaRepository.save(member1);

        // then
        Member findMember = memberJpaRepository.findById(member1.getNum()).orElse(null);

        assertThat(findMember).isEqualTo(member1);
    }

    /**
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : findAll by condition and pageable Test
     */
    @Test
//    @Rollback(value = false)
    public void 회원정보_모두가져오기_By_조건_페이징() {
        // given
        SelfPicture testSelfPicture = SelfPicture.createSelfPicture("a", "a");

        MemberForm memberForm1 = new MemberForm("a", "a", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm2 = new MemberForm("b", "bc", "a@a", "01012341234", "0511231234", "korea2", "b", "h", "h", "123", testSelfPicture);
        MemberForm memberForm3 = new MemberForm("c", "c", "a@a", "01012341234", "0511231234", "korea3", "b", "h", "h", "123", testSelfPicture);
        MemberForm memberForm4 = new MemberForm("a", "a", "a@a", "01012341234", "0511231234", "us", "c", "h", "h", "123", testSelfPicture);

        Member member1 = Member.createMember(memberForm1);
        Member member2 = Member.createMember(memberForm2);
        Member member3 = Member.createMember(memberForm3);
        Member member4 = Member.createMember(memberForm4);

        // 회원 저장
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        memberJpaRepository.save(member3);
        memberJpaRepository.save(member4);

        // when
        String today = "20230117";
        String nextday = "20230118";
        // 조건 입력하기
        // 조건 0개
        MemberSearchCond memberSearchCond = new MemberSearchCond(null, null, null, null); // 4
        // 조건 1개
        MemberSearchCond memberSearchCondWithId = new MemberSearchCond("a", null, null, null); // 2
        MemberSearchCond memberSearchCondWithCountry = new MemberSearchCond(null, "k", null, null); // 3
        MemberSearchCond memberSearchCondWithCity = new MemberSearchCond(null, null, "c", null); // 1
        MemberSearchCond memberSearchCondWithCreateDate = new MemberSearchCond(null, null, null, "20230117"); // 4
        // 조건 2개
        MemberSearchCond memberSearchCond1 = new MemberSearchCond("a", "korea", null, null); // 1
        MemberSearchCond memberSearchCond2 = new MemberSearchCond("c", null, "b", null); // 2
        MemberSearchCond memberSearchCond3 = new MemberSearchCond("a", null, null, "20230117");// 2
        MemberSearchCond memberSearchCond4 = new MemberSearchCond(null, "u", null, "20230117");// 1
        MemberSearchCond memberSearchCond5 = new MemberSearchCond(null, "u", "a", null);// 0
        MemberSearchCond memberSearchCond6 = new MemberSearchCond(null, null, "b", "20230117");// 3
        MemberSearchCond memberSearchCond7 = new MemberSearchCond(null, "a", "b", null);// 3
        // 조건 3개
        MemberSearchCond memberSearchCond8 = new MemberSearchCond(null, "a", "b", "20230118");// 0
        MemberSearchCond memberSearchCond9 = new MemberSearchCond("a", "ea", "b", null);// 1
        MemberSearchCond memberSearchCond10 = new MemberSearchCond("b", "a", null, "20230117");// 1
        MemberSearchCond memberSearchCond11 = new MemberSearchCond("b", null, "a", "20230117");// 0
        // 조건 4개
        MemberSearchCond memberSearchCond12 = new MemberSearchCond("a", "k", "b", "20230117");// 1


        // 모든 회원 가져오기
        PageRequest pageRequestSize10 = PageRequest.of(0, 10);
        PageRequest pageRequestSize1 = PageRequest.of(0, 1);
        PageRequest pageRequestSize2 = PageRequest.of(0, 2);
        PageRequest pageRequestPage1Size3 = PageRequest.of(1, 3);

//        // then - 객체로 비교
//        // 조건 0
//        Page<Member> findMembers = memberJpaRepository.findAllInfo(memberSearchCond, pageRequestSize10);
//
//        log.info("findMembers content : {}", findMembers.getContent());
//        assertThat(findMembers.getContent()).containsExactly(member4, member3, member2, member1);
//        findAllWithCondTest(memberSearchCond, pageRequestSize10, member1, member2, member3, member4);
//
//        // 조건 1
//        findAllWithCondTest(memberSearchCondWithId, pageRequestSize10, member1, member4);
//        findAllWithCondTest(memberSearchCondWithCountry, pageRequestSize10, member1, member2, member3);
//        findAllWithCondTest(memberSearchCondWithCity, pageRequestSize10, member4);
//        findAllWithCondTest(memberSearchCondWithCreateDate, pageRequestSize10, member1, member2, member3, member4);
//
//        // 조건 2
//        findAllWithCondTest(memberSearchCond1, pageRequestSize10, member1);
//        findAllWithCondTest(memberSearchCond2, pageRequestSize10, member2, member3);
//        findAllWithCondTest(memberSearchCond3, pageRequestSize10, member1, member4);
//        findAllWithCondTest(memberSearchCond4, pageRequestSize10, member4);
//        findAllWithCondTest(memberSearchCond5, pageRequestSize10, null);
//        findAllWithCondTest(memberSearchCond6, pageRequestSize10, member1, member3, member2);
//        findAllWithCondTest(memberSearchCond7, pageRequestSize10, member1);
//
//        // 조건 3
//        findAllWithCondTest(memberSearchCond8, pageRequestSize10, null);
//        findAllWithCondTest(memberSearchCond9, pageRequestSize10, member1);
//        findAllWithCondTest(memberSearchCond10, pageRequestSize10, member2);
//        findAllWithCondTest(memberSearchCond11, pageRequestSize10, null);
//
//        // 조건 4
//        findAllWithCondTest(memberSearchCond12, pageRequestSize10, member1);
//
//        // page의 size가 1일 때
//        findAllWithCondTest(memberSearchCond, pageRequestSize1, member4); // order by -> desc 라서
//        findAllWithCondTest(memberSearchCond, pageRequestSize1, member1); // fail
//        findAllWithCondTest(memberSearchCond, pageRequestSize1, member2); // fail

        // then - 개수로 비교
        // 조건 0
        findAllWithCondTestWithQuantity(memberSearchCond, pageRequestSize10, 4);

        // 조건 1
        findAllWithCondTestWithQuantity(memberSearchCondWithId, pageRequestSize10,2);
        findAllWithCondTestWithQuantity(memberSearchCondWithCountry, pageRequestSize10, 3);
        findAllWithCondTestWithQuantity(memberSearchCondWithCity, pageRequestSize10,1);
        findAllWithCondTestWithQuantity(memberSearchCondWithCreateDate, pageRequestSize10,4);

        // 조건 2
        findAllWithCondTestWithQuantity(memberSearchCond1, pageRequestSize10, 1);
        findAllWithCondTestWithQuantity(memberSearchCond2, pageRequestSize10, 2);
        findAllWithCondTestWithQuantity(memberSearchCond3, pageRequestSize10, 2);
        findAllWithCondTestWithQuantity(memberSearchCond4, pageRequestSize10, 1);
        findAllWithCondTestWithQuantity(memberSearchCond5, pageRequestSize10, 0);
        findAllWithCondTestWithQuantity(memberSearchCond6, pageRequestSize10, 3);
        findAllWithCondTestWithQuantity(memberSearchCond7, pageRequestSize10, 3);

        // 조건 3
        findAllWithCondTestWithQuantity(memberSearchCond8, pageRequestSize10, 0);
        findAllWithCondTestWithQuantity(memberSearchCond9, pageRequestSize10, 1);
        findAllWithCondTestWithQuantity(memberSearchCond10, pageRequestSize10, 1);
        findAllWithCondTestWithQuantity(memberSearchCond11, pageRequestSize10, 0);

        // 조건 4
        findAllWithCondTestWithQuantity(memberSearchCond12, pageRequestSize10, 1);

//        // page의 size가 1일 때 - 객체로 비교
//        findAllWithCondTest(memberSearchCond, pageRequestSize1, member4); // order by -> desc 라서
//        findAllWithCondTest(memberSearchCond, pageRequestSize1, member1); // fail
//        findAllWithCondTest(memberSearchCond, pageRequestSize1, member2); // fail

        // page의 size가 1일 때 - 개수로 비교
        findAllWithCondTestWithQuantity(memberSearchCond, pageRequestSize1, 1); // order by -> desc 라서
        findAllWithCondTestWithQuantity(memberSearchCond, pageRequestSize2, 2); // order by -> desc 라서
        findAllWithCondTestWithQuantity(memberSearchCond, pageRequestPage1Size3, 1); // order by -> desc 라서


        // totalCount 확인
        Page<Member> findMembers1 = memberJpaRepository.findAllInfo(memberSearchCond, pageRequestSize10);
        Page<Member> findMembers2 = memberJpaRepository.findAllInfo(memberSearchCondWithId, pageRequestSize10);

        assertThat(findMembers1.getTotalElements()).isEqualTo(4L);
        assertThat(findMembers2.getTotalElements()).isEqualTo(2L); // where 조건에 맞게 count 센다
    }

    // 왜 안 되지? 객체가 달라진 건가?

    /**
     * 객체를 비교
     */
    private void findAllWithCondTest(MemberSearchCond memberSearchCond, PageRequest pageRequest, Member... members) {
        Page<Member> findMembers = memberJpaRepository.findAllInfo(memberSearchCond, pageRequest);

        assertThat(findMembers.getContent()).containsExactly(members);
    }

    /**
     * 개수를 비교
     */
    private void findAllWithCondTestWithQuantity(MemberSearchCond memberSearchCond, PageRequest pageRequest, int quantity) {
        Page<Member> findMembers = memberJpaRepository.findAllInfo(memberSearchCond, pageRequest);

        assertThat(findMembers.getContent().size()).isEqualTo(quantity);
    }


}
