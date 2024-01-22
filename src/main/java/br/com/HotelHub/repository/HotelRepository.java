package br.com.HotelHub.repository;

import br.com.HotelHub.model.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelModel, Long> {
  /*  Optional<HotelModel> findByQuartoModelIdAndValorModelId(Long quarto_id, Long valor_id);*/
}