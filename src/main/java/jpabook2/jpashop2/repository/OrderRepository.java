package jpabook2.jpashop2.repository;


import jpabook2.jpashop2.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // -> Querydsl로 변환(복잡하거나, 동적 SQL용)
    // TODO : 여기 createQuery 때문에 select가 제대로 안됨.
    public List<Order> findAll(OrderSearch orderSearch){
        return em.createQuery("SELECT o FROM Order o JOIN o.member m" +
                " WHERE o.status = :status " +
                "AND m.name LIKE :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)    // TOP(1000)11
                .getResultList();
    }
}
