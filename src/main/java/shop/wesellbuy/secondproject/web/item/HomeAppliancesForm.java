package shop.wesellbuy.secondproject.web.item;

import lombok.Getter;

/**
 * Item(HomeAppliances) dto
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 클라이언트에게서 받은 상품(HomeAppliances) 정보를 담아둔다.
 */
@Getter
public class HomeAppliancesForm extends ItemForm{
    private String company;
}
