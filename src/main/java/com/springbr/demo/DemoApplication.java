package com.springbr.demo;

import com.springbr.demo.entities.Cliente;
import com.springbr.demo.entities.Pedido;
import com.springbr.demo.repository.ClienteRepository;
import com.springbr.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository){
		return args -> {
			Cliente c1 = new Cliente("Guilherme");
			clienteRepository.save(c1);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
