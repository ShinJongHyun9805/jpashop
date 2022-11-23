package jpabook2.jpashop2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name ="order_id")
    private Long id;

    // Order의 입장에서 Member는 다대일 관계(여러개의 주문을 한 하나의 고객)
    // FetchType EAGER을 사용하게 되면 N + 1 즉 Select 쿼리문 100개가 나간다고함.
    // 무조건 지연로딩 LAZY를 사용해야함.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id") // FK가 member_id가 된다.
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>(); // 필드에서 초기화하는게 최고

    // *ToONe은 다 지연로딩 해야함.
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;         // 주문상태 [ORDER, CANCEL]

    /**
     * 연관관계 메서드
     **/
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
       orderItems.add(orderItem);
       orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}