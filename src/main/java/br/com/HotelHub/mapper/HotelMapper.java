package br.com.HotelHub.mapper;

import br.com.HotelHub.dto.HotelDTO;
import br.com.HotelHub.dto.QuartoDTO;
import br.com.HotelHub.dto.ValorDTO;
import br.com.HotelHub.model.HotelModel;
import br.com.HotelHub.model.QuartoModel;
import br.com.HotelHub.model.ValorModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    public HotelDTO listaHoteis(HotelModel hotelModel, List<QuartoModel> quartoModels, ValorModel valorModel) {
        return HotelDTO.builder()
                .nome(hotelModel.getNome())
                .valorDTO(toMapValorDTO(valorModel))
                .servicos(hotelModel.getServicos())
                .quartoDTO(toMapQuartoDTO(quartoModels).stream().findFirst().orElse(null))
                .build();
    }

    private ValorDTO toMapValorDTO(ValorModel valorModel) {
        if (valorModel != null) {
            return ValorDTO.builder()
                    .valor(valorModel.getValor())
                    .porcetagemDeDesconto(valorModel.getPorcetagemDeDesconto())
                    .desconto(valorModel.isDesconto())
                    .build();
        } else {
            return ValorDTO.builder().build();
        }
    }

    private List<QuartoDTO> toMapQuartoDTO(List<QuartoModel> quartoModels) {
        return quartoModels.stream()
                .map(quartoModel -> QuartoDTO.builder()
                        .quarto(quartoModel.getQuarto())
                        .numeroDeCamas(quartoModel.getNumeroDeCamas())
                        .build())
                .collect(Collectors.toList());
    }
}
