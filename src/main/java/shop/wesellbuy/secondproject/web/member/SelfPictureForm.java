package shop.wesellbuy.secondproject.web.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class SelfPictureForm {

    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // DB에 저장된 파일 이름
}
