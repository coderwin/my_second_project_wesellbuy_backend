package shop.wesellbuy.secondproject.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.wesellbuy.secondproject.domain.Order;

import java.util.Optional;

public interface OrderJpaRepositoryCustom {

    /**
     * writer : 이호진
     * init : 2023.01.19
     * updated by writer : 이호진
     * update :
     * description :
     */
    Page<Order> findAllInfo(OrderSearchCond orderSearchCond, Pageable pageable);

    /**
     * writer : 이호진
     * init : 2023.01.19
     * updated by writer :
     * update :
     * description : 주문 상세보기 + fetchjoin by num(id)
     */
    Optional<Order> findDetailInfoById(int num);
}
