package br.com.HotelHub.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private String nome;

    private String servicos;

    private QuartoDTO quartoDTO;

    private ValorDTO valorDTO;
}
