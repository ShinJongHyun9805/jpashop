package jpabook2.jpashop2.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")    // Item에 싱글테이블 전략이라 어떤 애인지 구분지음
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
