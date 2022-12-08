package jpabook2.jpashop2.domain.item;

import jpabook2.jpashop2.domain.Category;
import jpabook2.jpashop2.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // Item에 싱글테이블 전략이라 어떤 애인지 구분지음
@Getter /*@Setter*/
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name ="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> catergories = new ArrayList<>();


    /**
     * 아래처럼 Setter가 아닌 비즈니스 로직으로 한 이유는
     * 가장 객체지향적 코딩이라고 함
    * */

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock =  this.stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }

}
