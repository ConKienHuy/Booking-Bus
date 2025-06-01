package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.schedule.*;
import sgu.spring.backend.exception.EntityInactiveException;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.ScheduleMapper;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.model.Schedule;
import sgu.spring.backend.model.Station;
import sgu.spring.backend.repository.BusRepository;
import sgu.spring.backend.repository.ScheduleRepository;
import sgu.spring.backend.repository.StationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private ScheduleMapper scheduleMapper;
    private StationRepository stationRepository;
    private BusRepository busRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, StationRepository stationRepository, BusRepository busRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.stationRepository = stationRepository;
        this.busRepository = busRepository;
    }

    public List<ScheduleResponse> getAll() {
        List<Schedule> scheduleList = scheduleRepository.findAllActive();
        return scheduleMapper.scheduleListToScheduleResponseList(scheduleList);
    }

    public ScheduleResponse getById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule ID: " + id + " not found."));

        if (!schedule.isEnable()) {
            throw new EntityInactiveException("Schedule ID: " + id + " not active.");
        }

        return scheduleMapper.scheduleToScheduleResponse(schedule);
    }

    public AddScheduleResponse create(AddScheduleRequest addScheduleRequest) {
        Bus bus = busRepository.findById(addScheduleRequest.getBusId())
                .orElseThrow(() -> new EntityNotFoundException("Bus ID: " + addScheduleRequest.getBusId() + " not found."));

        Station stationFrom = stationRepository.findById(addScheduleRequest.getStationFromId())
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + addScheduleRequest.getStationFromId() + " not found."));

        Station stationTo = stationRepository.findById(addScheduleRequest.getStationToId())
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + addScheduleRequest.getStationToId() + " not found."));

        Schedule schedule = scheduleMapper.addScheduleRequestToSchedule(addScheduleRequest);
        schedule.setBus(bus);
        schedule.setStationFrom(stationFrom);
        schedule.setStationTo(stationTo);
        schedule.setEnable(true);
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());

        scheduleRepository.save(schedule);
        return scheduleMapper.scheduleToAddScheduleResponse(schedule);
    }

    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest updateScheduleRequest) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule ID: " + id + " not found."));

        if (!existingSchedule.isEnable()) {
            throw new EntityInactiveException("Schedule ID: " + id + " not active.");
        }

        Bus bus = busRepository.findById(updateScheduleRequest.getBusId())
                .orElseThrow(() -> new EntityNotFoundException("Bus ID: " + updateScheduleRequest.getBusId() + " not found."));

        Station stationFrom = stationRepository.findById(updateScheduleRequest.getStationFromId())
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + updateScheduleRequest.getStationFromId() + " not found."));

        Station stationTo = stationRepository.findById(updateScheduleRequest.getStationToId())
                .orElseThrow(() -> new EntityNotFoundException("Station ID: " + updateScheduleRequest.getStationToId() + " not found."));


        scheduleMapper.updateScheduleRequestToSchedule(existingSchedule, updateScheduleRequest);
        existingSchedule.setUpdatedAt(LocalDateTime.now());
        existingSchedule.setBus(bus);
        existingSchedule.setStationFrom(stationFrom);
        existingSchedule.setStationTo(stationTo);

        scheduleRepository.save(existingSchedule);
        return scheduleMapper.scheduleToUpdateScheduleResponse(existingSchedule);
    }

    public ScheduleResponse delete(Long id) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule with ID " + id + " not found."));

        existingSchedule.setEnable(false);
        existingSchedule.setUpdatedAt(LocalDateTime.now());

        scheduleRepository.save(existingSchedule);
        return scheduleMapper.scheduleToScheduleResponse(existingSchedule);
    }
}
