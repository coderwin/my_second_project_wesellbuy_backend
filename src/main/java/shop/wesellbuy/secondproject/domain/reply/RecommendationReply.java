package shop.wesellbuy.secondproject.domain.reply;

import jakarta.persistence.*;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.CustomerService;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.Recommendation;
import shop.wesellbuy.secondproject.web.reply.ReplyForm;

/**
 * 추천합니다 board 댓글
 * writer : 이호진
 * init : 2023.01.15
 * updated by writer :
 * update :
 * description : 추천합니다. 게시판 댓글 정의한다.
 */
@Entity
@Getter
public class RecommendationReply {

    @Id
    @GeneratedValue
    @Column(name = "recommendationReply_num")
    private Integer num; // 댓글 번호
    private String comment; // 내용
    @Enumerated(value = EnumType.STRING)
    private ReplyStatus status; // 게시판 댓글 상태[REGISTER/DELETE]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_num")
    private Recommendation recommendation;

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
        member.getRecommendationReplyList().add(this);
    }

    // Recommendation
    public void addRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
        recommendation.getRecommendationReplyList().add(this);
    }

    // ** 생성 메서드 ** //
    public static RecommendationReply createRecommendationReply(ReplyForm replyForm, Member member, Recommendation Recommendation) {

        RecommendationReply recommendationReply = new RecommendationReply();

        recommendationReply.addComment(replyForm.getComment());
        recommendationReply.addStatus(ReplyStatus.R);
        recommendationReply.addMember(member);
        recommendationReply.addRecommendation(Recommendation);

        return recommendationReply;
    }
}
