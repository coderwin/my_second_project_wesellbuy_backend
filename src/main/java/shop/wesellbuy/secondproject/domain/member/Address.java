package shop.wesellbuy.secondproject.domain.member;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 주소
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 회원 주소를 정의한다.
 */
@Embeddable
@Getter
@AllArgsConstructor
public class Address {

    private String country; // 나라 이름
    private String city; // 지역 이름
    private String street; // 동
    private String detail; // 상세주소
    private String zipcode; // 우편보호

    // ** setter ** //

    // ** 생성 메서드 ** //
    public static Address createAddress(String country,
                                        String city,
                                        String street,
                                        String detail,
                                        String zipcode) {

        Address address = new Address(country, city, street, detail, zipcode);
        return address;
    }


}
