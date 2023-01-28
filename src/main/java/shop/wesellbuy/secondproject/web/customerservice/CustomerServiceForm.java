package shop.wesellbuy.secondproject.web.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 고객지원글 dto
 * writer : 이호진
 * init : 2023.01.28
 * updated by writer :
 * update :
 * description : 클라이언트에게서 받은 고객지원글 정보를 담아둔다.
 */
@Getter
@AllArgsConstructor
public class CustomerServiceForm {

    private String reportedId; // 신고된 회원 아이디
    private String content; // 신고 내용
}
