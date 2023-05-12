package com.springbr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository){
//		return args -> {
//			Cliente c1 = new Cliente("Guilherme");
//			clienteRepository.save(c1);
//		};
//	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
