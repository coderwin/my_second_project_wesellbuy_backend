package shop.wesellbuy.secondproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.wesellbuy.secondproject.domain.common.BaseDateColumnEntity;
import shop.wesellbuy.secondproject.domain.order.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 회원주문 정보
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 회원 주문정보를 정의한다.
 */
@Entity
@Table(name = "orders")
@Getter
public class Order extends BaseDateColumnEntity {

    @Id @GeneratedValue
    @Column(name = "order_num")
    private Integer num; // 주문 번호
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status; // 주문 상태[주문 상태(ORDER)인지, 주문 취소 상태(CANCEL)]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;// 회원 num

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "delivery_num")
    private Delivery delivery; // 배달 정보

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItemList = new ArrayList<>(); // 주문 아이템 모음

    // ** setter ** //
    public void addOrderStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    // ** 연관관계 메서드 ** //
    // Member
    public void addMember(Member member) {
        member.getOrderList().add(this);
        this.member = member;
    }

    // Delivery
    public void addDelivery(Delivery delivery) {
        delivery.addOrder(this);
        this.delivery = delivery;
    }

    // OrderItemList
    public void addOrderItems(OrderItem orderItem) {
        orderItem.addOrder(this);
        this.orderItemList.add(orderItem);
    }

    // ** 생성 메서드 ** //
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();

        order.addMember(member);
        order.addDelivery(delivery);
        // orderItem에 order 주입
        Arrays.stream(orderItems)
                .forEach(oi -> order.addOrderItems(oi));
        order.addOrderStatus(OrderStatus.O);

        return order;
    }
}
