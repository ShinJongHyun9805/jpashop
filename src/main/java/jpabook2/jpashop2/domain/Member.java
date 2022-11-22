package jpabook2.jpashop2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;            // ID

    private String name;        // 회원명

    @Embedded // 내장 타입을 포함했다라는 의미의 어노테이션
    private Address address;    // 주소

    /**
     * Member의 입장에서 Order는 일대다의 관계(한 명의 고객이 여러 개의 주문)
     * 현재 Order와 Member는 양방향 관계임으로 연관관계에서의 주인을 정해야함.
     * 이 강의의 TABLE 설계에는 order의 member_id가 FK로 설정
     * 그래서 위의 FK는 그대로 두고 아래 orders 변수에 mappedBy에 member 선언으로
     * 매핑을 하는 주체가 아닌 읽기전용이라고 생각하면 됨. (난 매핑된 애의 거울이야)
     * */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>(); // 주문리스트
}
