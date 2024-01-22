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
@Table(name = "quarto_model")
public class QuartoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quarto_id;

    @NotNull
    private String quarto;
    @NotNull
    private Long numeroDeCamas;

    @ManyToOne
    @JoinColumn(name = "hotel")
    @JsonIgnore
    private HotelModel hotelModel;
}
