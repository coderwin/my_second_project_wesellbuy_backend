package shop.wesellbuy.secondproject.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;
import shop.wesellbuy.secondproject.domain.recommendation.RecommendationPicture;

@Entity
@Getter
public class ItemPicture extends BaseDateColumnEntity {

    @Id
    @GeneratedValue
    @Column(name = "itemPicture_id")
    private Integer num; // 이미지 번호
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // DB에 저장된 파일 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_num")
    private Item item; // 회원 번호

    // ** setter ** //

    public void addOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public void addStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public void addItem(Item item) {
        this.item = item;
    }

//    // ** 연관관계 메서드 ** //
    // Item
//    public void addItem(Item item) {
//        this.item = item;
//        item.getItemPictureList().add(this);
//    }

    // ** 생성 메서드 ** //
    public static ItemPicture createItemPicture(String originalFileName, String storedFileName) {
        ItemPicture itemPicture = new ItemPicture();

        itemPicture.addOriginalFileName(originalFileName);
        itemPicture.addStoredFileName(storedFileName);

        return itemPicture;
    }
}
