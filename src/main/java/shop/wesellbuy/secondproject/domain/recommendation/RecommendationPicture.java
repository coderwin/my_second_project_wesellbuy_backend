package shop.wesellbuy.secondproject.domain.recommendation;

import jakarta.persistence.*;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.Recommendation;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;

@Entity
@Getter
public class RecommendationPicture extends BaseDateColumnEntity {

    @Id
    @GeneratedValue
    @Column(name = "itemPicture_id")
    private Integer num; // 이미지 번호
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // DB에 저장된 파일 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_num")
    private Recommendation recommendation; // 추천합니다 번호

    // ** setter ** //
    public void addRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }
}
