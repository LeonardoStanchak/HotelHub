package br.com.HotelHub.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDTO {

    private String quarto;

    private Long numeroDeCamas;
}
