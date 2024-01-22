package br.com.HotelHub;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger Hotel", version = "1", description = "API para salvar e ver os hoteis"))
public class HotelHubConectandoExperienciasEReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelHubConectandoExperienciasEReservasApplication.class, args);
	}

}
