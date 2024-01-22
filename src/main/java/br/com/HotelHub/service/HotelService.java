package br.com.HotelHub.service;

import br.com.HotelHub.dto.HotelDTO;
import br.com.HotelHub.model.HotelModel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    HotelModel novoHotel(HotelModel hotelModel);

    List<HotelDTO> hoteis();

    HotelModel atualizaDadosDoHotel(Long id, HotelModel novosDados);

    void removendoHotel(Long id, Long ids);

     Optional<HotelModel> hotelId(Long id);
}
