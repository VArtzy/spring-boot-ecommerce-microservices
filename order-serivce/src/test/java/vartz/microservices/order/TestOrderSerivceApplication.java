package vartz.microservices.order;

import org.springframework.boot.SpringApplication;

public class TestOrderSerivceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderSerivceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
