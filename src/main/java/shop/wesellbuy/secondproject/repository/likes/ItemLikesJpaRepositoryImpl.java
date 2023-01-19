package shop.wesellbuy.secondproject.repository.likes;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.wesellbuy.secondproject.domain.likes.ItemLikes;

import java.util.List;

import static shop.wesellbuy.secondproject.domain.QItem.item;
import static shop.wesellbuy.secondproject.domain.QMember.member;
import static shop.wesellbuy.secondproject.domain.likes.QItemLikes.itemLikes;

/**
 * ItemLikesJpaRepositoryCustom 구현
 * writer : 이호진
 * init : 2023.01.19
 * updated by writer :
 * update :
 * description : ItemLikesJpaRepository 구현 모음 + 최적화 사용(fetch)
 *
 * comment : item 좋아요 많은 1,2,3 어떻게 출력할지 고민
 */
@RequiredArgsConstructor
@Slf4j
public class ItemLikesJpaRepositoryImpl implements ItemLikesJpaRepositoryCustom{

    private final JPAQueryFactory query;

    /**
     * writer : 이호진
     * init : 2023.01.19
     * updated by writer : 이호진
     * update : 2023.01.18
     * description : 모든 상품_좋아요 찾기 + fetchjoin
     *               ...ById == ...ByNum
     *
     * comment : 무엇을 select할지 결정 필요(itemLikes? or item? => service에서 결정하자)
     *           -> 필요 없을수도 있다(item을 select한 후, service에서 itemLikes를 dto에 담는다)
     */
    @Override
    public List<ItemLikes> findAllInfoById(int memberNum) {

        return query
                .selectFrom(itemLikes)
                .join(itemLikes.member, member).fetchJoin()
                .join(itemLikes.item, item).fetchJoin()
                .where(itemLikes.member.num.eq(memberNum))
                .fetch();
    }
}
