package shop.wesellbuy.secondproject.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.wesellbuy.secondproject.domain.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Integer>, OrderJpaRepositoryCustom {
}
