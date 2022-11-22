package jpabook2.jpashop2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable       // 내장 함수로 쓰겠다.
@Getter
public class Address {

    private String city;    // 도시
    private String street;  //
    private String zipcode; // 우편번호

    // JPA는 생성자 사용 시 기본 생성자 선언해야함.
    // protected는 기본 생성자를 호출하는 실수를 방지하기 위해
    protected Address() {
    }

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
