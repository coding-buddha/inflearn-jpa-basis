package edu.pasudo123.study.inflearn.orders.service;

import edu.pasudo123.study.inflearn.delivery.model.Delivery;
import edu.pasudo123.study.inflearn.item.model.Item;
import edu.pasudo123.study.inflearn.item.repository.ItemRepository;
import edu.pasudo123.study.inflearn.member.model.Member;
import edu.pasudo123.study.inflearn.member.repository.MemberRepository;
import edu.pasudo123.study.inflearn.orders.model.Order;
import edu.pasudo123.study.inflearn.orders.model.OrderItem;
import edu.pasudo123.study.inflearn.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    public Long order(final Long memberId, final Long itemId, final int count) {

        // 엔티티 조회
        Member foundMember = memberRepository.findById(memberId);
        Item foundItem = itemRepository.findById(itemId);

        // 배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setAddress(foundMember.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(foundItem, foundItem.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(foundMember, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    public void cancelOrder(final Long orderId) {
        Order order = orderRepository.findAllById(orderId);
        // 더티체킹 작용
        order.cancel();
    }

//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }


}
