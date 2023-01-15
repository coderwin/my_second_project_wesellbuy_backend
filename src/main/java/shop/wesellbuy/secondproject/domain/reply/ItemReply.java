package shop.wesellbuy.secondproject.domain.reply;

import jakarta.persistence.*;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.CustomerService;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;
import shop.wesellbuy.secondproject.web.reply.ReplyForm;

/**
 * Item(board) 댓글
 * writer : 이호진
 * init : 2023.01.15
 * updated by writer :
 * update :
 * description : Item(게시판) 댓글 정의한다.
 */
@Entity
@Getter
public class ItemReply extends BaseDateColumnEntity {

    @Id
    @GeneratedValue
    @Column(name = "itemReply_num")
    private Integer num; // 댓글 번호
    private String content; // 내용
    @Enumerated(value = EnumType.STRING)
    private ReplyStatus status; // 게시판 댓글 상태[REGISTER/DELETE]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_num")
    private Item item;

    // ** setter ** //
    public void addContent(String content) {
        this.content = content;
    }

    // ** 연관관계 메서드 ** //
    // Member
    public void addMember(Member member) {
        this.member = member;
        member.getItemReplyList().add(this);
    }

    public void addStatus(ReplyStatus status) {
        this.status = status;
    }

    // Item
    public void addItem(Item item) {
        this.item = item;
        item.getItemReplyList().add(this);
    }


    // ** 생성 메서드 ** //
    public static ItemReply createItemReply(ReplyForm replyForm, Member member, Item Item) {

        ItemReply itemReply = new ItemReply();

        itemReply.addContent(replyForm.getContent());
        itemReply.addStatus(ReplyStatus.R);
        itemReply.addMember(member);
        itemReply.addItem(Item);

        return itemReply;
    }
}
