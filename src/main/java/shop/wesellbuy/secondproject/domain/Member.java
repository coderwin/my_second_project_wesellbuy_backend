package shop.wesellbuy.secondproject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    String name;

    protected Member() {
    }

    public Member(String name) {
        this.name = name;
    }
}
