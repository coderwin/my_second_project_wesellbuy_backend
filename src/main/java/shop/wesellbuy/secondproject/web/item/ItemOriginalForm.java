package shop.wesellbuy.secondproject.web.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.item.ItemPicture;

import java.util.ArrayList;
import java.util.List;

/**
 * Item original dto
 * writer : 이호진
 * init : 2023.02.02
 * updated by writer :
 * update :
 * description : 클라이언트에게서 받은 상품 정보를 담아둔다.
 */
@Getter
@AllArgsConstructor
public class ItemOriginalForm {

    private String name; // 상품명
    private Integer stock; // 제고 수량
    private Integer price; // 가격
    private String content; // 설명
    private String type; // 상풍종류 설정
    private List<ItemPicture> itemPictureList = new ArrayList<>(); // 상품 이미지 모음

    // Book에 필요
    private String author; // 저자
    private String publisher; // 출판사

    // Furniture, HomeAppliances에 필요
    private String company;// 제조회사 이름

    // ** 비즈니스 메서드 **//

    /**
     * 상품 이미지 list 담기
     */
    public void addItemPictureList(List<ItemPicture> itemPictureList) {
        if(!itemPictureList.isEmpty()) {
            this.itemPictureList = itemPictureList;
        }
    }
}
