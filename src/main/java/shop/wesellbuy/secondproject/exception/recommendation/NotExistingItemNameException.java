package shop.wesellbuy.secondproject.exception.recommendation;

/**
 * 사용자 정의 예외
 * writer : 이호진
 * init : 2023.01.28
 * updated by writer :
 * update :
 * description : 추천합니다글 작성 중 존재하지 않는 상품이면 예외 발생
 */
public class NotExistingItemNameException extends RuntimeException{
    public NotExistingItemNameException() {
        super();
    }

    public NotExistingItemNameException(String message) {
        super(message);
    }

    public NotExistingItemNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistingItemNameException(Throwable cause) {
        super(cause);
    }

    protected NotExistingItemNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
