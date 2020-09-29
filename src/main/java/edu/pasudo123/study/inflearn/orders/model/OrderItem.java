package edu.pasudo123.study.inflearn.orders.model;

import edu.pasudo123.study.inflearn.item.rmodel.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문가격

    private int count;      // 주문수량
}
