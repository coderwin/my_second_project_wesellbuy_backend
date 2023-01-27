package shop.wesellbuy.secondproject.repository.likes;

import com.querydsl.core.Tuple;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.QItem;
import shop.wesellbuy.secondproject.domain.QMember;
import shop.wesellbuy.secondproject.domain.item.Book;
import shop.wesellbuy.secondproject.domain.likes.ItemLikes;
import shop.wesellbuy.secondproject.web.item.BookForm;
import shop.wesellbuy.secondproject.web.member.MemberForm;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static shop.wesellbuy.secondproject.domain.likes.QItemLikes.itemLikes;

@SpringBootTest
@Transactional
@Slf4j
public class ItemLikesJpaRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ItemLikesJpaRepository itemLikesJpaRepository;

    Member member; // 등록 회원
    Member member2; // 등록 회원
    Member member3; // 등록 회원
    Member member4; // 등록 회원
    Member member5; // 등록 회원
    Member member6; // 등록 회원
    Member member7; // 등록 회원
    Member member8; // 등록 회원
    Member member9; // 등록 회원
    Member member10; // 등록 회원

    Item item; // 등록 상품
    Item item2; // 등록 상품
    Item item3; // 등록 상품

    // create member/customerService object
    @BeforeEach
    public void init() {

        // 회원 정보 저장
        MemberForm memberForm1 = new MemberForm("a", "a","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm2 = new MemberForm("b", "b","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm3 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm4 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm5 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm6 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm7 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm8 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm9 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm10 = new MemberForm("c", "c","123", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);

        Member member = Member.createMember(memberForm1);
        Member member2 = Member.createMember(memberForm2);
        Member member3 = Member.createMember(memberForm3);
        Member member4 = Member.createMember(memberForm4);
        Member member5 = Member.createMember(memberForm5);
        Member member6 = Member.createMember(memberForm6);
        Member member7 = Member.createMember(memberForm7);
        Member member8 = Member.createMember(memberForm8);
        Member member9 = Member.createMember(memberForm9);
        Member member10 = Member.createMember(memberForm10);

        em.persist(member);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);
        em.persist(member6);
        em.persist(member7);
        em.persist(member8);
        em.persist(member9);
        em.persist(member10);


        this.member = member;
        this.member2 = member2;
        this.member3 = member3;
        this.member4 = member4;
        this.member5 = member5;
        this.member6 = member6;
        this.member7 = member7;
        this.member8 = member8;
        this.member9 = member9;
        this.member10 = member10;

        // 상품 저장
        BookForm bookForm = new BookForm(10, 1000, "x", "x is...", null, "ed", "ok");
        item = Book.createBook(bookForm, member);
        BookForm bookForm2 = new BookForm(10, 1000, "x", "x is...", null, "ed", "ok");
        item2 = Book.createBook(bookForm2, member2);
        BookForm bookForm3 = new BookForm(10, 1000, "x", "x is...", null, "ed", "ok");
        item3 = Book.createBook(bookForm3, member3);

        em.persist(item);
        em.persist(item2);
        em.persist(item3);
    }

    @Test
//    @Rollback(false)
    public void 상품_좋아요_저장() {
        // given
        ItemLikes itemLikes = ItemLikes.createItemLikes(member, item);

        // when
        itemLikesJpaRepository.save(itemLikes);

        // then
        ItemLikes findItemLikes = itemLikesJpaRepository.findById(itemLikes.getNum()).orElseThrow();
        assertThat(findItemLikes).isEqualTo(itemLikes);
    }

    @Test
//    @Rollback(false)
    public void 상품_좋아요_삭제() {
        // given
        ItemLikes itemLikes = ItemLikes.createItemLikes(member, item);

        em.persist(itemLikes);
        // when1
        // then
        ItemLikes findItemLikes = itemLikesJpaRepository.findById(itemLikes.getNum()).orElseThrow();
        assertThat(findItemLikes).isEqualTo(itemLikes);

        // when2
        // delete
        itemLikesJpaRepository.delete(findItemLikes);

        em.flush();
        em.clear();
        // then
        assertThrows(NoSuchElementException.class, () -> itemLikesJpaRepository.findById(itemLikes.getNum()).orElseThrow());
        assertThatThrownBy(() -> itemLikesJpaRepository.findById(itemLikes.getNum()).orElseThrow())
                .isInstanceOf(NoSuchElementException.class);

    }

    @Test
//    @Rollback(false)
    public void 모두_가져오기_상품_좋아요_by_memberNum() {

        // given
        ItemLikes itemLikes = ItemLikes.createItemLikes(member, item);
        ItemLikes itemLikes2 = ItemLikes.createItemLikes(member, item2);
        ItemLikes itemLikes3 = ItemLikes.createItemLikes(member, item3);
        ItemLikes itemLikes4 = ItemLikes.createItemLikes(member2, item3);

        em.persist(itemLikes);
        em.persist(itemLikes2);
        em.persist(itemLikes3);
        em.persist(itemLikes4);
        // when
        List<ItemLikes> itemLikesList = itemLikesJpaRepository.findAllInfoById(member.getNum());
        List<ItemLikes> itemLikesList2 = itemLikesJpaRepository.findAllInfoById(member2.getNum());

        // then
        assertThat(itemLikesList).containsExactly(itemLikes, itemLikes2, itemLikes3);
        assertThat(itemLikesList2).containsExactly(itemLikes4);
    }

    @Test
//    @Rollback(false)
    public void 상품_좋아요_개수에따른_순위_확인() {
        // given
        // 좋아요 만들기
        // item 좋아요
        em.persist(ItemLikes.createItemLikes(member, item));
        em.persist(ItemLikes.createItemLikes(member2, item));
        em.persist(ItemLikes.createItemLikes(member3, item));
        em.persist(ItemLikes.createItemLikes(member4, item));
        em.persist(ItemLikes.createItemLikes(member5, item));
        // item2 좋아요
        em.persist(ItemLikes.createItemLikes(member, item2));
        em.persist(ItemLikes.createItemLikes(member2, item2));
        em.persist(ItemLikes.createItemLikes(member3, item2));
        em.persist(ItemLikes.createItemLikes(member4, item2));
        em.persist(ItemLikes.createItemLikes(member5, item2));
//        em.persist(ItemLikes.createItemLikes(member6, item2));
//        em.persist(ItemLikes.createItemLikes(member7, item2));
//        em.persist(ItemLikes.createItemLikes(member8, item2));
//        em.persist(ItemLikes.createItemLikes(member9, item2));
//        em.persist(ItemLikes.createItemLikes(member10, item2));
        // item3 좋아요
        em.persist(ItemLikes.createItemLikes(member, item3));
        em.persist(ItemLikes.createItemLikes(member2, item3));
        em.persist(ItemLikes.createItemLikes(member3, item3));

        // when
        // item 좋아요 개수 가져오기
        List<Tuple> result = itemLikesJpaRepository.findRank();

//        QMember m2 = new QMember("m");
//        QItem i2 = new QItem("i");

        // then
        assertThat(result.get(0).get(itemLikes.count())).isEqualTo(5L);
        assertThat(result.get(0).get(QMember.member).getId()).isEqualTo(member.getId());
        assertThat(result.get(0).get(QItem.item).getName()).isEqualTo(item.getName());
        assertThat(result.get(0).get(QItem.item)).isEqualTo(item);

        assertThat(result.get(1).get(itemLikes.count())).isEqualTo(5L);
        assertThat(result.get(1).get(QMember.member).getId()).isEqualTo(member2.getId());
        assertThat(result.get(1).get(QItem.item).getName()).isEqualTo(item2.getName());
        assertThat(result.get(1).get(QItem.item)).isEqualTo(item2);

        assertThat(result.get(2).get(itemLikes.count())).isEqualTo(3L);
        assertThat(result.get(2).get(QMember.member).getId()).isEqualTo(member3.getId());
        assertThat(result.get(2).get(QItem.item).getName()).isEqualTo(item3.getName());
        assertThat(result.get(2).get(QItem.item)).isEqualTo(item3);

        int i = 1;
        for(Tuple subResult : result) {

            log.info("Tuple " + i + ": {}", subResult);
            i++;
        }

        var isTrue = false;
        while(!isTrue) {
            log.info("member: " + member);
            log.info("member2: " + member2);
            log.info("member3: " + member3);

            isTrue = true;
        }
    }

    @Test
//    @Rollback(false)
    public void 상품_좋아요_개수에따른_순위_확인_V2() {
        // given
        // 좋아요 만들기
        // item 좋아요
        em.persist(ItemLikes.createItemLikes(member, item));
        em.persist(ItemLikes.createItemLikes(member2, item));
        em.persist(ItemLikes.createItemLikes(member3, item));
        em.persist(ItemLikes.createItemLikes(member4, item));
        em.persist(ItemLikes.createItemLikes(member5, item));
        // item2 좋아요
        em.persist(ItemLikes.createItemLikes(member, item2));
        em.persist(ItemLikes.createItemLikes(member2, item2));
        em.persist(ItemLikes.createItemLikes(member3, item2));
        em.persist(ItemLikes.createItemLikes(member4, item2));
        em.persist(ItemLikes.createItemLikes(member5, item2));
//        em.persist(ItemLikes.createItemLikes(member6, item2));
//        em.persist(ItemLikes.createItemLikes(member7, item2));
//        em.persist(ItemLikes.createItemLikes(member8, item2));
//        em.persist(ItemLikes.createItemLikes(member9, item2));
//        em.persist(ItemLikes.createItemLikes(member10, item2));
        // item3 좋아요
        em.persist(ItemLikes.createItemLikes(member, item3));
        em.persist(ItemLikes.createItemLikes(member2, item3));
        em.persist(ItemLikes.createItemLikes(member3, item3));

        // when
        // item 좋아요 개수 가져오기
        // rank 버전 by using dto
        List<ItemRankDto> result = itemLikesJpaRepository.findRankV2();

        // then
        int i = 0;
        Long[] values = {5L, 5L, 3L};
        // 좋아요 개수 비교
        for(ItemRankDto subResult : result) {
            log.info("ItemRankDto " + i + ": {}", subResult);
            assertThat(result.get(i).getCount()).isEqualTo(values[i]);
            i++;
        }
    }




}
