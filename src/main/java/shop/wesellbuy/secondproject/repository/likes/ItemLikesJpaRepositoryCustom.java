package shop.wesellbuy.secondproject.repository.likes;

import com.querydsl.core.Tuple;
import shop.wesellbuy.secondproject.domain.likes.ItemLikes;

import java.util.List;

/**
 * ItemLikes Repository by using queryDsl
 * writer : 이호진
 * init : 2023.01.19
 * updated by writer :
 * update :
 * description : querydsl이용한 ItemLikesJpaRepository 모음
 */
public interface ItemLikesJpaRepositoryCustom {

    /**
     * writer : 이호진
     * init : 2023.01.19
     * updated by writer : 이호진
     * update : 2023.01.18
     * description : 모든 상품_좋아요 찾기 + fetchjoin
     *               ...ById == ...ByNum
     */
    List<ItemLikes> findAllInfoById(int memberNum);

    /**
     * writer : 이호진
     * init : 2023.01.20
     * updated by writer : 이호진
     * update : 2023.01.18
     * description : 모든 상품_좋아요 or 좋아요 많은 순위 찾기
     *               -> item service에서 사용한다.
     */
    List<Tuple> findRank();


}
