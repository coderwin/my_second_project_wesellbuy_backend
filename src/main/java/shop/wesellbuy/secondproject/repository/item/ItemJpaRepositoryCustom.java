package shop.wesellbuy.secondproject.repository.item;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.wesellbuy.secondproject.domain.Item;

import java.util.Optional;

/**
 * Item Repository by using queryDsl
 * writer : 이호진
 * init : 2023.01.19
 * updated by writer :
 * update :
 * description : querydsl이용한 ItemJpaRepository 모음
 */
public interface ItemJpaRepositoryCustom {
    /**
     * writer : 이호진
     * init : 2023.01.19
     * updated by writer : 이호진
     * update : 2023.01.18
     * description : 모든 추천합니다 게시글 찾기 + fetchjoin
     */
    Page<Item> findAllInfo(ItemSearchCond itemSearchCond, Pageable pageable);

    /**
     * writer : 이호진
     * init : 2023.01.19
     * updated by writer : 이호진
     * update : 2023.01.18
     * description : 상품 상세보기 + fetchjoin by num(id)
     */
    Optional<Item> findDetailInfoById(int num);
}
