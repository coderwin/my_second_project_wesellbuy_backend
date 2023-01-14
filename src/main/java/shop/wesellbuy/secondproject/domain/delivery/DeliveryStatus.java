package shop.wesellbuy.secondproject.domain.delivery;

/**
 * 배송 상태 정보
 * writer : 이호진
 * init : 2023.01.14
 * updated by writer :
 * update :
 * description : 배송 상태(배송준비중(READY), 배송중(TRANSIT), 배송완료(COMPLETE))알려준다.
 */
public enum DeliveryStatus {
    R("READY"),
    T("TRANSIT"),
    C("COMPLETE");

    private final String deliveryStatus; // 배송 상태

    DeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }


}
