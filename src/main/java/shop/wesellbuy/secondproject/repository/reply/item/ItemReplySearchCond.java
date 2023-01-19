package shop.wesellbuy.secondproject.repository.reply.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ItemReply findAll for condition dto
 * writer : 이호진
 * init : 2023.01.19
 * updated by writer :
 * update :
 * description : ItemReply finaAll에 사용되는 where 절의 조건 데이터 모음
 */
@Getter
@AllArgsConstructor
public class ItemReplySearchCond {

    private String memberId; // 작성자 id
    private String content; // 작성 내용
    private String createDate; // 작성날짜
}
