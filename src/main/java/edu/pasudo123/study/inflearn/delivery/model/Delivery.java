package edu.pasudo123.study.inflearn.delivery.model;

import edu.pasudo123.study.inflearn.member.model.Address;
import edu.pasudo123.study.inflearn.orders.model.Order;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}