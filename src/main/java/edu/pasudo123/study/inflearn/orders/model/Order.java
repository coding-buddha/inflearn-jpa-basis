package edu.pasudo123.study.inflearn.orders.model;

import edu.pasudo123.study.inflearn.delivery.model.Delivery;
import edu.pasudo123.study.inflearn.member.model.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;         // 주문상태 [ORDERS, CANCEL]

    // 연관관계 편의메소드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCurrentOrderDate() {
        this.orderDate = LocalDateTime.now();
    }

    // 생성메소드 : static
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.orderItems.addAll(Arrays.asList(orderItems));
        order.setStatus(OrderStatus.ORDER);
        order.setCurrentOrderDate();
        return order;
    }

    /**
     * 주문 취소
     */
    public void cancel() {
        if(delivery.isComp()) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        this.orderItems.forEach(OrderItem::cancel);
    }

    /**
     * 전체 주문가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        return this.orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
