package br.com.HotelHub.service;

import br.com.HotelHub.dto.HotelDTO;
import br.com.HotelHub.model.HotelModel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    HotelModel novoHotel(HotelModel hotelModel);

    List<HotelDTO> hoteis();

   /* Optional<HotelModel> hotelId(Long id, Long quarto_id, Long valor_id);*/
}
