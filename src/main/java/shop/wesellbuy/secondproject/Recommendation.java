package shop.wesellbuy.secondproject;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;
import shop.wesellbuy.secondproject.domain.recommendation.RecommendationPicture;
import shop.wesellbuy.secondproject.web.recommendation.RecommendationForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 추천합니다 board
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
public class Recommendation extends BaseDateColumnEntity {

    @Id @GeneratedValue
    @Column(name = "customerService_num")
    private Integer num;
    private String ItemName; // 추천받은 상품 이름
    private String sellerId; // 추천받은 판매자 이름
    private String content; // 추천 이유

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member; // 회원 아이디

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.PERSIST) // 생명주기 같다
    private List<RecommendationPicture> recommendationPictureList = new ArrayList<>();

    // ** setter ** //
    public void addItemName(String itemName) {
        ItemName = itemName;
    }

    public void addSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void addContent(String content) {
        this.content = content;
    }

    // ** 연관관계 메서드 ** //
    // Member
    public void addMember(Member member) {
        this.member = member;
        member.getRecommendationList().add(this);
    }

    // RecommendationPicture
    public void addRecommendationPicture(RecommendationPicture recommendationPicture) {
        recommendationPicture.addRecommendation(this);
        this.recommendationPictureList.add(recommendationPicture);
    }

    // ** 생성 메서드 ** //
    public static Recommendation createRecommendation(RecommendationForm recommendationForm, Member member) {
        Recommendation recommendation = new Recommendation();

        recommendation.addItemName(recommendationForm.getItemName());
        recommendation.addSellerId(recommendationForm.getSellerId());
        recommendation.addContent(recommendationForm.getContent());
        recommendation.addMember(member);

        // RecommendationPicture에 Recommendation 등록
        recommendationForm.getRecommendationPictureList()
                .forEach((rp) -> rp.getRecommendation().addRecommendationPicture(rp));

        return recommendation;
    }

}
