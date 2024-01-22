package br.com.HotelHub.service.impl;

import br.com.HotelHub.dto.HotelDTO;
import br.com.HotelHub.mapper.HotelMapper;
import br.com.HotelHub.model.HotelModel;
import br.com.HotelHub.model.QuartoModel;
import br.com.HotelHub.model.ValorModel;
import br.com.HotelHub.repository.HotelRepository;
import br.com.HotelHub.repository.QuartoRepository;
import br.com.HotelHub.repository.ValorRepository;
import br.com.HotelHub.service.HotelService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private QuartoRepository quartoRepository;
    private ValorRepository valorRepository;

    private HotelMapper hotelMapper;

    private Gson gson;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper, QuartoRepository quartoRepository, ValorRepository valorRepository, Gson gson ){
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.quartoRepository = quartoRepository;
        this.valorRepository = valorRepository;
        this.gson = gson;
    }

    @Override
    public HotelModel novoHotel(HotelModel hotelModel) {
        QuartoModel quartoModel = hotelModel.getQuartoModel();
        log.info("os dados passadno para o quarto são {}", gson.toJson(quartoModel));
        ValorModel valorModel = hotelModel.getValorModel();
        log.info("os dados passadno para o valor são {}", gson.toJson(valorModel));
        if (quartoModel != null && valorModel != null){
            log.info("os dados do quarto e do valor não estão null");
            quartoModel = quartoRepository.save(quartoModel);
            hotelModel.setQuartoModel(quartoModel);
            log.info("quarto do hotel salvo com sucesso {}", gson.toJson(hotelModel.getQuartoModel()));
            valorModel = valorRepository.save(valorModel);
            hotelModel.setValorModel(valorModel);
            log.info("O valor do quarto do hotel foi salvo com sucesso {}", gson.toJson(hotelModel.getValorModel()));
        }
        return hotelRepository.save(hotelModel);
    }

    @Override
    public List<HotelDTO> hoteis() {
        List<HotelModel> hotelModelList = hotelRepository.findAll();

        log.info("busca todos os hoteis {}", gson.toJson(hotelModelList));

        // Mapeia os quartos por ID do hotel
        Map<Long, List<QuartoModel>> quartoModelMap = quartoRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(QuartoModel::getQuarto_id));

        log.info("realiza um map para buscarmos os quartos do hotel {}", gson.toJson(quartoModelMap));

        // Mapeia os valores por ID do hotel
        Map<Long, ValorModel> valorModelMap = valorRepository.findAll()
                .stream()
                .collect(Collectors.toMap(ValorModel::getValor_id, v -> v));

        log.info("realiza um map para buscarmos os valores do hotel {}", gson.toJson(valorModelMap));

        return hotelModelList.stream()
                .map(hotelModel -> {
                    List<QuartoModel> quartoModels = quartoModelMap.getOrDefault(hotelModel.getId(), Collections.emptyList());
                    ValorModel valorModel = valorModelMap.get(hotelModel.getId());

                    return hotelMapper.listaHoteis(hotelModel, quartoModels, valorModel);
                })
                .collect(Collectors.toList());
    }

    @Override
    public HotelModel atualizaDadosDoHotel(Long id, HotelModel novosDados) {
        Optional<HotelModel> buscandoHotel = hotelRepository.findById(id);

        if (buscandoHotel.isPresent()){
            HotelModel hotelModel = buscandoHotel.get();
            hotelModel.setServicos(novosDados.getServicos());
            hotelModel.setValorModel(novosDados.getValorModel());
            hotelModel.setQuartoModel(novosDados.getQuartoModel());
            log.info("os dados novos deixaram a classe assim {}", gson.toJson(hotelModel));
            return hotelRepository.save(hotelModel);
        } else {
            throw new IllegalArgumentException("Beneficiário não encontrado com o ID: " + id);
        }
    }

    @Override
    public void removendoHotel(Long id, Long ids) {
        Optional<HotelModel> hotelModel = hotelRepository.findById(id);

        if (hotelModel.isPresent()){
            quartoRepository.deleteById(ids);
            valorRepository.deleteById(ids);
            hotelRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Beneficiário não encontrado com o ID: " + id);
        }
    }
    @Override
    public Optional<HotelModel> hotelId(Long id) {
        /*Optional<QuartoModel> quartoModel = quartoRepository.findById(quarto_id);
        log.info("buscando os quartos pelo id do hotel {}", gson.toJson(quartoModel));
        Optional<ValorModel> valorModel = valorRepository.findById(valor_id);
        log.info("buscando os valores pelo id do hotel {}", gson.toJson(valorModel));*/

        return hotelRepository.findById(id);
    }
}
