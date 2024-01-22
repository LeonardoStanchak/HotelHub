package br.com.HotelHub.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorDTO {

    private String valor;

    private boolean desconto;

    private String porcetagemDeDesconto;
}
