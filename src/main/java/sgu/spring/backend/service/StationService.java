package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.station.StationRequest;
import sgu.spring.backend.dto.station.StationResponse;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.StationMapper;
import sgu.spring.backend.model.Station;
import sgu.spring.backend.repository.StationRepsitory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StationService {
    private StationRepsitory stationRepository;
    private StationMapper stationMapper;

    @Autowired
    public StationService(StationRepsitory stationRepository, StationMapper stationMapper) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
    }

    public List<StationResponse> getAll() {
        List<Station> stationList = stationRepository.findAll();
        return stationMapper.toDTOList(stationList);
    }

    public StationResponse getById(Long id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station with id not found."));

        return stationMapper.toDTO(station);
    }

    public StationResponse create(StationRequest stationRequest) {
        Station station = stationMapper.toEntity(stationRequest);
        station.setStatus(1);
        station.setCreatedAt(LocalDateTime.now());
        station.setUpdatedAt(LocalDateTime.now());

        station = stationRepository.save(station);
        return stationMapper.toDTO(station);
    }

    public StationResponse update(Long id ,StationRequest stationRequest){
        Station existedStation = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station with id not found."));

        existedStation = stationMapper.updateEntity(existedStation ,stationRequest);
        existedStation.setUpdatedAt(LocalDateTime.now());
        existedStation = stationRepository.save(existedStation);

        return stationMapper.toDTO(existedStation);
    }

    public StationResponse delete(Long id){
        Station existedStation = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station with id not found."));

        existedStation.setStatus(0);
        existedStation.setUpdatedAt(LocalDateTime.now());
        existedStation = stationRepository.save(existedStation);
        return stationMapper.toDTO(existedStation);
    }

}
