package vartz.microservices.inventory;

import org.springframework.boot.SpringApplication;

public class TestInventorySerivceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventorySerivceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
