package shop.wesellbuy.secondproject.repository.likes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.item.Book;
import shop.wesellbuy.secondproject.domain.likes.ItemLikes;
import shop.wesellbuy.secondproject.web.item.BookForm;
import shop.wesellbuy.secondproject.web.member.MemberForm;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Slf4j
public class ItemLikeJpaRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ItemLikesJpaRepository itemLikesJpaRepository;

    Member member; // 등록 회원
    Member member2; // 등록 회원
    Member member3; // 등록 회원

    Item item; // 등록 상품
    Item item2; // 등록 상품
    Item item3; // 등록 상품

    // create member/customerService object
    @BeforeEach
    public void init() {

        // 회원 정보 저장
        MemberForm memberForm1 = new MemberForm("a", "a", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm2 = new MemberForm("b", "b", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        MemberForm memberForm3 = new MemberForm("c", "c", "a@a", "01012341234", "0511231234", "korea1", "b", "h", "h", "123", null);
        Member member = Member.createMember(memberForm1);
        Member member2 = Member.createMember(memberForm2);
        Member member3 = Member.createMember(memberForm3);

        em.persist(member);
        em.persist(member2);
        em.persist(member3);

        this.member = member;
        this.member2 = member2;
        this.member3 = member3;

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
    @Rollback(false)
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


}
