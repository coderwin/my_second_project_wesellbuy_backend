package shop.wesellbuy.secondproject.repository.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.wesellbuy.secondproject.domain.delivery.DeliveryStatus;
import shop.wesellbuy.secondproject.domain.order.OrderStatus;

@Getter
@AllArgsConstructor
public class OrderSearchCond {

    private String memberId; // 주문 회원
    private String orderStatus; // 주문 상태
    private String deliveryStatus; // 배달 상태
    private String createDate;// 생성 날짜
}
