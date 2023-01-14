package shop.wesellbuy.secondproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;

/**
 * 고객지원 board
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 고객지원 게시판을 정의한다.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerService extends BaseDateColumnEntity {

    @Id @GeneratedValue
    @Column(name = "customerService_num")
    private Integer num;
    private String reportedId; // 신고된 회원 아이디
    private String content; // 신고 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member; // 신고한 회원 아이디

    // ** 연관관계 메서드 ** //
    public void addMember(Member member) {
        this.member = member;
        member.getCustomerServiceList().add(this);
    }
}
