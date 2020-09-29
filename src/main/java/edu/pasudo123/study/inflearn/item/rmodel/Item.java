package edu.pasudo123.study.inflearn.item.rmodel;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity; // 주문수량
}
