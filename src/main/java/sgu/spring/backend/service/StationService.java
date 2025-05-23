package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.station.*;
import sgu.spring.backend.exception.EntityInactiveException;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.StationMapper;
import sgu.spring.backend.model.Station;
import sgu.spring.backend.repository.StationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    @Autowired
    public StationService(StationRepository stationRepository, StationMapper stationMapper) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
    }

    public List<StationResponse> getAll() {
        List<Station> stationList = stationRepository.findAllActive();
        return stationMapper.stationListToStationResponseList(stationList);
    }

    public StationResponse getById(Long id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + id + " not found."));

        if(station.isEnable() == false) {
            throw new EntityInactiveException("Station ID: " + id + " not active.");
        }

        return stationMapper.stationToStationResponse(station);
    }

    public AddStationResponse create(AddStationRequest stationRequest) {
        Station station = stationMapper.addStationRequestToStation(stationRequest);

        station.setEnable(true);
        station.setCreatedAt(LocalDateTime.now());
        station.setUpdatedAt(LocalDateTime.now());

        station = stationRepository.save(station);
        return stationMapper.stationToAddStationResponse(station);
    }

    public UpdateStationResponse update(Long id, UpdateStationRequest stationRequest) {
        Station existedStation = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + id + " not found."));

        if(existedStation.isEnable() == false) {
            throw new EntityInactiveException("Station ID: " + id + " not active.");
        }

        stationMapper.updateStationRequestToStation(existedStation, stationRequest);
        existedStation.setUpdatedAt(LocalDateTime.now());

        existedStation = stationRepository.save(existedStation);
        return stationMapper.stationToUpdateStationResponse(existedStation);
    }

    public StationResponse delete(Long id) {
        Station existedStation = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + id + " not found."));

        existedStation.setEnable(false);
        existedStation.setUpdatedAt(LocalDateTime.now());

        existedStation = stationRepository.save(existedStation);
        return stationMapper.stationToStationResponse(existedStation);
    }
}
