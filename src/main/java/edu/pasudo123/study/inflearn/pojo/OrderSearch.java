package edu.pasudo123.study.inflearn.pojo;

import edu.pasudo123.study.inflearn.orders.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;          // 회원 이름
    private OrderStatus orderStatus;    // 주문 상태 [ORDER, CANCEL]
}
