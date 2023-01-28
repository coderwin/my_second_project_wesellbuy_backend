package shop.wesellbuy.secondproject.web.recommendation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.recommendation.RecommendationPicture;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RecommendationForm {

    private String ItemName; // 추천받은 상품 이름
    private String sellerId; // 추천받은 판매자 이름
    private String content; // 추천 이유
    private List<RecommendationPicture> recommendationPictureList;

    // ** 비즈니스 로직 ** //

    public void addRecommendationPictureList(List<RecommendationPicture> recommendationPictureList) {
        this.recommendationPictureList = recommendationPictureList;
    }
}
