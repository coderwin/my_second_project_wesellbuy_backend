package shop.wesellbuy.secondproject.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.Item;
import shop.wesellbuy.secondproject.domain.Member;
import shop.wesellbuy.secondproject.web.item.HomeAppliancesForm;
import shop.wesellbuy.secondproject.web.item.ItemForm;

@Entity
@DiscriminatorValue("HA")
@Getter
public class HomeAppliances extends Item {

    private String company;// 제조회사 이름

    // ** setter ** //
    public void addCompany(String company) {
        this.company = company;
    }

    // ** 생성 메서드 ** //
    // item controller 만들 때, 나중에 다시 생각
    public static HomeAppliances createHomeAppliances(HomeAppliancesForm homeAppliancesForm, Member member) {
        HomeAppliances homeAppliances = new HomeAppliances();

        homeAppliances.addStock(homeAppliancesForm.getStock());
        homeAppliances.addPrice(homeAppliancesForm.getPrice());
        homeAppliances.addName(homeAppliancesForm.getName());
        homeAppliances.addContent(homeAppliancesForm.getContent());
        homeAppliances.addStatus(ItemStatus.R);
        homeAppliances.addMember(member);
        homeAppliances.addCompany(homeAppliances.getCompany());
        // 각각의 itemPicture에 item 등록
        if(homeAppliancesForm.getItemPictureList() != null) {
//            homeAppliancesForm.getItemPictureList().forEach((ip) -> ip.addItem(homeAppliances));
            homeAppliancesForm.getItemPictureList().forEach((ip) -> homeAppliances.addItemPictures(ip));
        }

        return homeAppliances;
    }

}
