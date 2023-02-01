package shop.wesellbuy.secondproject.service.likes;

import org.springframework.transaction.annotation.Transactional;
import shop.wesellbuy.secondproject.web.likes.ItemLikesListForm;

import java.util.List;

/**
 * ItemLikes Service
 * writer : 이호진
 * init : 2023.02.01
 * updated by writer :
 * update :
 * description : ItemLikes Service 메소드 모음
 */
public interface ItemLikesService {

    /**
     * writer : 이호진
     * init : 2023.02.01
     * updated by writer :
     * update :
     * description : 상품 좋아요 저장
     */
    int save(int itemNum, int memberNum);

    /**
     * writer : 이호진
     * init : 2023.02.01
     * updated by writer :
     * update :
     * description : 상품 좋아요 삭제
     */
    void delete(int num);

    /**
     * writer : 이호진
     * init : 2023.02.01
     * updated by writer :
     * update :
     * description : 모든 상품 좋아요 불러오기 by memberNum
     */
    List<ItemLikesListForm> selectList(int memberNum);
}
