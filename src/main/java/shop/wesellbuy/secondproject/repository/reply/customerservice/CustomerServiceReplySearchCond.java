package shop.wesellbuy.secondproject.repository.reply.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CustomerServiceReply findAll for condition dto
 * writer : 이호진
 * init : 2023.01.17
 * updated by writer :
 * update :
 * description : CustomerServiceReply finaAll에 사용되는 where 절의 조건 데이터 모음
 */
@Getter
@AllArgsConstructor
public class CustomerServiceReplySearchCond {

    private String memberId; // 작성자 id
    private String content; // 작성 내용
    private String createDate; // 작성날짜
}
