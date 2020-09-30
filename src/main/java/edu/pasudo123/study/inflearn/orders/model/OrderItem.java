package edu.pasudo123.study.inflearn.orders.model;

import edu.pasudo123.study.inflearn.item.model.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문가격

    private int count;      // 주문수량

    public static OrderItem createOrderItem(final Item item, final int orderPrice, final int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.initItem(item);
        orderItem.initOrderPrice(orderPrice);
        orderItem.initCount(count);
        item.removeStock(count);
        return orderItem;
    }

    public void initItem(final Item item) {
        this.item = item;
    }

    public void initOrderPrice(final int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void initCount(final int count) {
        this.count = count;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void cancel() {
          getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
