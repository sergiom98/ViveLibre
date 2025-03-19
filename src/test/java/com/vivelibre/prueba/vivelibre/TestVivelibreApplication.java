package com.vivelibre.prueba.vivelibre;

import org.springframework.boot.SpringApplication;

public class TestVivelibreApplication {

	public static void main(String[] args) {
		SpringApplication.from(VivelibreApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
