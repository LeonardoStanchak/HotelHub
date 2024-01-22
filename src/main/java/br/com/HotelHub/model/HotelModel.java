package br.com.HotelHub.model;

import br.com.HotelHub.dto.QuartoDTO;
import br.com.HotelHub.dto.ValorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel_model")
public class HotelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nome;
    @NotNull
    private String servicos;
    @NotNull
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private QuartoModel quartoModel;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ValorModel valorModel;
}
