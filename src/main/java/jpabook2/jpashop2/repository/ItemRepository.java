package jpabook2.jpashop2.repository;


import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    // -> JPQL 테이블 검색이 아닌 클래스파일 검색
    public List<Item> findAll(){
        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }
}
