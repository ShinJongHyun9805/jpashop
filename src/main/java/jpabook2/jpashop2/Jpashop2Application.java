package jpabook2.jpashop2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jpashop2Application {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setHello("hi");
		String data = hello.getHello();
		System.out.println("--------------------------------" + data);

		SpringApplication.run(Jpashop2Application.class, args);
	}

}
