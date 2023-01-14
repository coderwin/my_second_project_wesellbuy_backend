package shop.wesellbuy.secondproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;
import shop.wesellbuy.secondproject.domain.item.ItemPicture;
import shop.wesellbuy.secondproject.web.item.ItemForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 상품 정보
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 회원이 입력한  상품 정보를 정의한다.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public class Item extends BaseDateColumnEntity {

    @Id @GeneratedValue
    @Column(name = "item_num")
    private Integer num; // 상품 번호
    private Integer stock; // 제고 수량
    private Integer price; // 가격
    private String content; // 설명

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST) // 생명주기가 같다
    private List<ItemPicture> itemPictureList = new ArrayList<>(); // 상품 이미지 모음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;

    // ** setter ** //


    public void addStock(Integer stock) {
        this.stock = stock;
    }

    public void addPrice(Integer price) {
        this.price = price;
    }

    public void addContent(String content) {
        this.content = content;
    }

    // ** 연관관계 메서드 ** //
    // Member
    public void addMember(Member member) {
        this.member = member;
        member.getItemList().add(this);
    }

    // ItemPicture
    public void addItemPictures(ItemPicture itemPicture) {
        itemPicture.addItem(this);
        this.itemPictureList.add(itemPicture);
    }

    // ** 생성 메서드 ** //
    // item controller 만들 때, 나중에 다시 생각
    public static Item createItem(ItemForm itemForm, Member member) {
        Item item = new Item();

        item.addStock(itemForm.getStock());
        item.addPrice(itemForm.getPrice());
        item.addContent(itemForm.getContent());
        item.addMember(member);
        // 각각의 itemPicture에 item 등록
        itemForm.getItemPictureList().forEach((ip) -> item.addItemPictures(ip));

        return item;
    }

    // ** 비즈니스 로직(메서드) ** //

    /**
     * 상품주문이 있으면 해당 item 제고량 빼주기
     */
    public void removeStock() {
    }
}
