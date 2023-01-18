package shop.wesellbuy.secondproject.repository.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CustomerService findAll for condition dto
 * writer : 이호진
 * init : 2023.01.17
 * updated by writer :
 * update :
 * description : CustomerService finaAll에 사용되는 where 절의 조건 데이터 모음
 */
@Getter
@AllArgsConstructor
public class CustomerServiceSearchCond {

    private String memberId; // 신고한 회원 아이디
    private String reportedId; // 신고당한 회원 아이디
    private String createDate; // create date




}
