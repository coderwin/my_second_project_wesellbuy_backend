package shop.wesellbuy.secondproject.service.recommendation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.Recommendation;
import shop.wesellbuy.secondproject.domain.recommendation.RecommendationPicture;
import shop.wesellbuy.secondproject.exception.recommendation.NotExistingItemNameException;
import shop.wesellbuy.secondproject.repository.item.ItemJpaRepository;
import shop.wesellbuy.secondproject.repository.member.MemberJpaRepository;
import shop.wesellbuy.secondproject.repository.recommendation.RecommendationJpaRepository;
import shop.wesellbuy.secondproject.service.customerservice.FileStoreOfRecommendationPicture;
import shop.wesellbuy.secondproject.web.recommendation.RecommendationDetailForm;
import shop.wesellbuy.secondproject.web.recommendation.RecommendationForm;
import shop.wesellbuy.secondproject.web.recommendation.RecommendationUpdateForm;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationJpaRepository recommendationJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final ItemJpaRepository itemJpaRepository;
    private final FileStoreOfRecommendationPicture fileStore; // 이미지 저장

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 추천합니다글 저장
     */
    @Override
    public int save(RecommendationForm recommendationForm, List<MultipartFile> files, int memberNum) throws IOException {
        // 추천받은 상품 & 판매자 있는지 확인
        checkItemNameAndSellerId(recommendationForm.getItemName(), recommendationForm.getSellerId());
        // 추천합니다 사진 서버 컴퓨터에 저장하기
        List<RecommendationPicture> recommendationPictureList = fileStore.storeFiles(files);
        // 생성된 사진 list recommendationForm에 담기
        recommendationForm.addRecommendationPictureList(recommendationPictureList);
        // 회원 불러오기
        Member member = memberJpaRepository.findById(memberNum).orElseThrow();
        // 추천합니다글 생성
        Recommendation recommendation = Recommendation.createRecommendation(recommendationForm, member);

        return recommendation.getNum();
    }

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 상품 & 판매자가 존재하는지 확인한다.
     */
    private void checkItemNameAndSellerId(String itemName, String memberId) {
        // 추천받은 상품 있는지 확인
        String errMsg = "추천 상품 또는 판매자를 잘못 입력하셨습니다.";
        itemJpaRepository.findByNameAndSellerId(itemName, memberId)
                .orElseThrow(() -> new NotExistingItemNameException(errMsg));
    }

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 추천합니다글 상세보기
     */
    @Override
    public RecommendationDetailForm watchDetail(int num) {
        // 추천합니다글 불러오기
        Recommendation recommendation = recommendationJpaRepository.findDetailInfoById(num).orElseThrow();
        // RecommendationDetailForm으로 변경하기
        return RecommendationDetailForm.create(recommendation);
    }

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 추천합니다글 조회수 1 증가
     */
    public void updateHits(int num) {
        // 추천합니다글 불러오기
        Recommendation recommendation = recommendationJpaRepository.findById(num).orElseThrow();
        // 조회수 1 증가 시키기
        recommendation.changeHits();
    }

    /**
     * writer : 이호진
     * init : 2023.01.28
     * updated by writer :
     * update :
     * description : 추천합니다글 수정
     */
    public void update(RecommendationUpdateForm recommendationUpdateForm,
                       List<MultipartFile> files) throws IOException {
        // 추천받은 상품 & 판매자 있는지 확인
        checkItemNameAndSellerId(recommendationUpdateForm.getItemName(), recommendationUpdateForm.getSellerId());
        // 추천합니다글 불러오기
        Recommendation recommendation = recommendationJpaRepository.findById(recommendationUpdateForm.getNum()).orElseThrow();
        // 이미지 서버 컴퓨터에 저장하기
        List<RecommendationPicture> newPictures = fileStore.storeFiles(files);
        // 수정하기
        recommendation.updateRecommendation(recommendationUpdateForm, newPictures);
    }



}
