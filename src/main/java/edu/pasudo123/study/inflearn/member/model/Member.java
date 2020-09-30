package edu.pasudo123.study.inflearn.member.model;

import edu.pasudo123.study.inflearn.orders.model.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Builder
    public Member(final String name, final Address address) {
        this.name = name;
        this.address = address;
    }
}
