package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.stationschedule.AddStationScheduleResponse;
import sgu.spring.backend.dto.stationschedule.StationScheduleResponse;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.StationScheduleMapper;
import sgu.spring.backend.model.StationSchedule;
import sgu.spring.backend.repository.StationScheduleRepository;

import java.util.List;

@Service
public class StationScheduleService {
    private StationScheduleRepository stationScheduleRepository;
    private StationScheduleMapper statioSchedulenMapper;

    @Autowired
    public StationScheduleService(StationScheduleRepository stationScheduleRepository, StationScheduleMapper stationScheduleMapper) {
        this.stationScheduleRepository = stationScheduleRepository;
        this.statioSchedulenMapper = stationScheduleMapper;
    }

    public List<StationScheduleResponse> getAll() {
        List<StationSchedule> stationScheduleList = stationScheduleRepository.findAllActive();
        return statioSchedulenMapper.toDTOList(stationScheduleList);
    }

    public StationScheduleResponse getById(Long id) {
        StationSchedule stationSchedule = stationScheduleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Station schedule ID: " +id+ " not found"));

        return statioSchedulenMapper.toDTO(stationSchedule);
    }

//    public AddStationScheduleResponse
}
