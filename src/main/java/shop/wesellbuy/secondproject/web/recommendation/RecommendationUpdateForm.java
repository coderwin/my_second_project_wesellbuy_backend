package shop.wesellbuy.secondproject.web.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 추천합니다글 수정 form dto
 * writer : 이호진
 * init : 2023.01.28
 * updated by writer :
 * update :
 * description : 클라이언트가 보내온 추천합니다글 수정 정보를 담아둔다.
 */
@Getter
@AllArgsConstructor
public class RecommendationUpdateForm {
    private Integer num; // 게시글 번호
    private String itemName; // 추천받은 상품 이름
    private String sellerId; // 추천받은 판매자 이름
    private String content; // 추천 이유
}
