package shop.wesellbuy.secondproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 클라이언트로 예외 전달 방법 3
 * writer : 이호진
 * init : 2023.02.10
 * updated by writer :
 * update :
 * description : API 에러를 객체(json)으로 던져준다
 *               > @Validated filed error를 넘겨준다.
 */
@Getter
@AllArgsConstructor
public class ValidatedErrorsMsg<T> {

    private T errors; // validated의 필드 에러 모음

}
