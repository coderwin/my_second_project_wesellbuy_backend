package shop.wesellbuy.secondproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.wesellbuy.secondproject.domain.delivery.DeliveryStatus;
import shop.wesellbuy.secondproject.domain.member.Address;

/**
 * 회원 가입 현황 정보
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 회원이 가입된 상태인지, 탈퇴한 상태인지 알려준다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_num")
    private Integer name; // 배달
    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status; // 배달 상태(배송준비중(R), 배송중(T), 배송완료(C))
    private Address address; // 배달 주소 === 회원 주소

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    // ** setter ** //


    public void addStatus(DeliveryStatus status) {
        this.status = status;
    }

    public void addAddress(Address address) {
        this.address = address;
    }

    public void addOrder(Order order) {
        this.order = order;
    }

    // ** 생성 메서드 ** /
    public static Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();

        delivery.addStatus(DeliveryStatus.R); // 주문 처음 => READY 상태
        delivery.addAddress(member.getAddress());

        return delivery;
    }


}
