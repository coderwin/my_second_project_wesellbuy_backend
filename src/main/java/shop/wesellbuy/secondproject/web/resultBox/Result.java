package shop.wesellbuy.secondproject.web.resultBox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Result <T> {

    private final T data;

}
