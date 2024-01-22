package br.com.HotelHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "valor_model")
public class ValorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long valor_id;

    @NotNull
    private String valor;

    @NotNull
    private boolean desconto;

    @NotNull
    private String porcetagemDeDesconto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel")
    @JsonIgnore
    private HotelModel hotelModel;
}
