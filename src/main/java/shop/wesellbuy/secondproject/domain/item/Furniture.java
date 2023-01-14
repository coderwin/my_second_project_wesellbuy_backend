package shop.wesellbuy.secondproject.domain.item;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.web.item.FurnitureForm;
import shop.wesellbuy.secondproject.web.item.HomeAppliancesForm;

@Entity
@DiscriminatorValue("F")
@Getter
public class Furniture extends Item {

    private String company;// 제조회사 이름

    // ** setter ** //
    public void addCompany(String company) {
        this.company = company;
    }

    // ** 생성 메서드 ** //
    // item controller 만들 때, 나중에 다시 생각
    public static Furniture createFurnitureForm(FurnitureForm furnitureForm, Member member) {
        Furniture furniture = new Furniture();

        furniture.addStock(furnitureForm.getStock());
        furniture.addPrice(furnitureForm.getPrice());
        furniture.addContent(furnitureForm.getContent());
        furniture.addMember(member);
        furniture.addCompany(furnitureForm.getCompany());
        // 각각의 itemPicture에 item 등록
        furniture.getItemPictureList().forEach((ip) -> furniture.addItemPictures(ip));

        return furniture;
    }
}
