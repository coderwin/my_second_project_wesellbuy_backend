package shop.wesellbuy.secondproject.domain.reply;

import jakarta.persistence.*;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.CustomerService;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.board.BoardStatus;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;
import shop.wesellbuy.secondproject.web.reply.ReplyForm;

/**
 * 고객지원 board 댓글
 * writer : 이호진
 * init : 2023.01.15
 * updated by writer :
 * update :
 * description : 고객지원 게시판 댓글 정의한다.
 */
@Entity
@Getter
public class CustomerServiceReply extends BaseDateColumnEntity {

    @Id @GeneratedValue
    @Column(name = "customerServiceReply_num")
    private Integer num; // 댓글 번호
    private String comment; // 내용
    @Enumerated(value = EnumType.STRING)
    private ReplyStatus status; // 게시판 댓글 상태[REGISTER/DELETE]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerService_num")
    private CustomerService customerService;

    // ** setter ** //
    public void addComment(String comment) {
        this.comment = comment;
    }

    public void addStatus(ReplyStatus status) {
        this.status = status;
    }

    // ** 연관관계 메서드 ** //
    // Member
    public void addMember(Member member) {
        this.member = member;
        member.getCustomerServiceReplyList().add(this);
    }

    // CustomerService
    public void addCustomerService(CustomerService customerService) {
        this.customerService = customerService;
        customerService.getCustomerServiceReplyList().add(this);
    }

    // ** 생성 메서드 ** //
    public static CustomerServiceReply createCustomerServiceReply(ReplyForm replyForm, Member member, CustomerService customerService) {

        CustomerServiceReply customerServiceReply = new CustomerServiceReply();

        customerServiceReply.addComment(replyForm.getComment());
        customerServiceReply.addStatus(ReplyStatus.R);
        customerServiceReply.addMember(member);
        customerServiceReply.addCustomerService(customerService);

        return customerServiceReply;
    }
}
