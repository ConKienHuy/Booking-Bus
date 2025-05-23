package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.schedule.*;
import sgu.spring.backend.exception.EntityInactiveException;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.ScheduleMapper;
import sgu.spring.backend.model.Schedule;
import sgu.spring.backend.repository.ScheduleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
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
        Schedule schedule = scheduleMapper.addScheduleRequestToSchedule(addScheduleRequest);

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

        scheduleMapper.updateScheduleRequestToSchedule(existingSchedule, updateScheduleRequest);

        existingSchedule.setUpdatedAt(LocalDateTime.now());

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
