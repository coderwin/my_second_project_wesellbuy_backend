package shop.wesellbuy.secondproject.repository.reply.customerservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.wesellbuy.secondproject.domain.CustomerService;
import shop.wesellbuy.secondproject.domain.reply.CustomerServiceReply;
import shop.wesellbuy.secondproject.repository.customerservice.CustomerServiceSearchCond;

/**
 * CustomerServiceReply Repository by using queryDsl
 * writer : 이호진
 * init : 2023.01.16
 * updated by writer :
 * update :
 * description : querydsl이용한 CustomerServiceJpaRepository 모음
 */
public interface CustomerServiceReplyJpaRepositoryCustom {

    /**
     * writer : 이호진
     * init : 2023.01.17
     * updated by writer :
     * update :
     * description : 모든 고객지원 게시글 찾기
     */
    public Page<CustomerServiceReply> findAllInfo(CustomerServiceReplySearchCond customerServiceReplySearchCond, Pageable pageable);
}
