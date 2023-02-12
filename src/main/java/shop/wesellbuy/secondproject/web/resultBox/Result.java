package shop.wesellbuy.secondproject.web.resultBox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 클라이언트 응답 data
 * writer : 이호진
 * init : 2023.01.15
 * updated by writer :
 * update :
 * description : 클라이언트에게서 보낼 데이터를 담아둔다.
 */
@ToString
@Getter
@AllArgsConstructor
public class Result <T> {

    private final T data;
    private int boardNum; // 게시글 번호 보내주기

    public Result(T data) {
        this.data = data;
    }
}
