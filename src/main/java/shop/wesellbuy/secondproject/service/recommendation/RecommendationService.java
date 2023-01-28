package shop.wesellbuy.secondproject.service.recommendation;

import org.springframework.web.multipart.MultipartFile;
import shop.wesellbuy.secondproject.web.recommendation.RecommendationDetailForm;
import shop.wesellbuy.secondproject.web.recommendation.RecommendationForm;

import java.io.IOException;
import java.util.List;

/**
 * Recommendation Service
 * writer : 이호진
 * init : 2023.01.28
 * updated by writer :
 * update :
 * description : Recommendation Service 메소드 모음
 */
public interface RecommendationService {

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 추천합니다글 저장
     */
    int save(RecommendationForm recommendationForm, List<MultipartFile> files, int memberNum) throws IOException;

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 추천합니다글 상세보기
     */
    RecommendationDetailForm watchDetail(int num);
}
