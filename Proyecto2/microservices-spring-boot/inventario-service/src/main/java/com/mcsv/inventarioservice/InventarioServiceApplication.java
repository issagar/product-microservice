package com.mcsv.inventarioservice;

import com.mcsv.inventarioservice.model.Inventario;
import com.mcsv.inventarioservice.repository.InventarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class InventarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventarioRepository inventarioRepository){
		return args -> {
			Inventario inventario = new Inventario();
			inventario.setCodigoSku("iphone_15");
			inventario.setCantidad(100);

			Inventario inventario2 = new Inventario();
			inventario2.setCodigoSku("iphone_blue");
			inventario2.setCantidad(0);

			inventarioRepository.save(inventario);
			inventarioRepository.save(inventario2);
		};
	}
}
