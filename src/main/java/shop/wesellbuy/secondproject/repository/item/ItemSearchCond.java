package shop.wesellbuy.secondproject.repository.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Item findAll for condition dto
 * writer : 이호진
 * init : 2023.01.19
 * updated by writer :
 * update :
 * description : Item finaAll에 사용되는 where 절의 조건 데이터 모음
 */
@Getter
@AllArgsConstructor
public class ItemSearchCond {

    private String name; // 상품명
    private String memberId; // 등록 아이디
    private String createDate; // 만든 날짜
    private String dtype; // 상품 종류
//    private Integer price; // 가격
//    private String upAndDown; // 가격 위/가격 아래

}
