package br.com.HotelHub.controller;

import br.com.HotelHub.dto.HotelDTO;
import br.com.HotelHub.model.HotelModel;
import br.com.HotelHub.service.HotelService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/hotelHub", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "HotelHub-Hoteis-Cadastrados")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private Gson gson;

    @PostMapping("/novo_hotel")
    @Operation(summary = "Registra um novo Hotel no nosso site", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registra um novo Hotel"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar registro de um novo Hotel"),
    })
    public ResponseEntity<HotelModel> hotelModelResponseEntity(@RequestBody HotelModel hotelModel){
        hotelModel = hotelService.novoHotel(hotelModel);
        log.info("Salvando um novo hotel na base de dados {}", gson.toJson(hotelModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelModel);
    }

    @GetMapping("/hoteis")
    @Operation(summary = "Busca todos os hoteis", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca todos os hoteis"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos hoteis"),
    })
    public ResponseEntity<List<HotelDTO>> hotelModelResponseEntity(){
        var hotel = hotelService.hoteis();
        log.info("Trazendo os dados dos hoteis que temos na base de dados {}", gson.toJson(hotel));
        return ResponseEntity.ok().body(hotel);
    }

    /*@GetMapping("/hoteis/{id}/{quartoID},{valorID}")
    @Operation(summary = "Busca todos os hoteis", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca todos os hoteis"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos hoteis"),
    })
    public ResponseEntity<Optional<HotelModel>> hotelModelResponse(@PathVariable Long id, @PathVariable Long quarto_id, @PathVariable Long valor_id){
        log.info("recebemos esses ids {}", gson.toJson(id), gson.toJson(quarto_id), gson.toJson(valor_id));
        var hotel = hotelService.hotelId(id, quarto_id, valor_id);
        log.info("Trazendo os dados dos hoteis que temos na base de dados {}", gson.toJson(hotel));
        return ResponseEntity.ok().body(hotel);
    }*/
}
