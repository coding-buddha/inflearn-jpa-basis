package edu.pasudo123.study.inflearn.delivery.model;

import edu.pasudo123.study.inflearn.member.model.Address;
import edu.pasudo123.study.inflearn.orders.model.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    @Setter
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isComp() {
        return status == DeliveryStatus.COMP;
    }
}
