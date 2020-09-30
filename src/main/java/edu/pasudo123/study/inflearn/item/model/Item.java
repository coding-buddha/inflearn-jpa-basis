package edu.pasudo123.study.inflearn.item.model;

import edu.pasudo123.study.inflearn.category.model.Category;
import edu.pasudo123.study.inflearn.exception.NotEnoughStockException;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    // DDD 설계
    // 재고 수량 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        if(stockQuantity - quantity < 0) {
            throw new NotEnoughStockException("재고 수량은 음수가 되지 못합니다.");
        }

        this.stockQuantity -= quantity;
    }
}
