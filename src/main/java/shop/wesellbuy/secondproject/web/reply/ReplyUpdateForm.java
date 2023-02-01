package shop.wesellbuy.secondproject.web.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * board 댓글 수정 dto
 * writer : 이호진
 * init : 2023.02.01
 * updated by writer :
 * update :
 * description : 클라이언트에게서 받은 댓글 수정 내용 정보를 담아둔다.
 */
@Getter
@AllArgsConstructor
public class ReplyUpdateForm {

    private int num; // 댓글 번호
    private String content; // 댓글 수정 내용
}
