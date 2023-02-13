package shop.wesellbuy.secondproject.utile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class PatternTest {

    @DisplayName("상품 저장 데이터 검증 패턴 테스트")
    @Test
    public void 상품_저장_데이터_검증_패턴_테스트() {
        // given
        String pattern1 = "^\\d+$"; // 숫자만 가능
        String pattern2 = "^B|HA|F|ITEM$"; // 이 단어만 가능
        // when
        String value1 = "123asdf";
        String value2 = "asdf!@2";
        String value3 = "01235";

        boolean result1 = Pattern.matches(pattern1, value1);
        boolean result2 = Pattern.matches(pattern1, value2);
        boolean result3 = Pattern.matches(pattern1, value3);

        String value21 = "asdf";
        String value22 = "HA";
        String value23 = "ITEM";
        String value24 = "ITEM2";
        String value25 = "B";
        String value26 = "BD";
        String value27 = "H";

        boolean result21 = Pattern.matches(pattern2, value21);
        boolean result22 = Pattern.matches(pattern2, value22);
        boolean result23 = Pattern.matches(pattern2, value23);
        boolean result24 = Pattern.matches(pattern2, value24);
        boolean result25 = Pattern.matches(pattern2, value25);
        boolean result26 = Pattern.matches(pattern2, value26);
        boolean result27 = Pattern.matches(pattern2, value27);

        // then
        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isTrue();

        assertThat(result21).isFalse();
        assertThat(result22).isTrue();
        assertThat(result23).isTrue();
        assertThat(result24).isFalse();
        assertThat(result25).isTrue();
        assertThat(result26).isFalse();
        assertThat(result27).isFalse();
    }
}
