package shop.wesellbuy.secondproject.web.reply;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * board 댓글 dto
 * writer : 이호진
 * init : 2023.01.15
 * updated by writer :
 * update :
 * description : 클라이언트에게서 받은 댓글 내용 정보를 담아둔다.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyForm {
    private String content; // 내용
}
