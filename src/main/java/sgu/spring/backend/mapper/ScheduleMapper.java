package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.schedule.*;
import sgu.spring.backend.model.Schedule;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StationMapper.class, BusMapper.class})
public interface ScheduleMapper {

    Schedule addScheduleRequestToSchedule(AddScheduleRequest addScheduleRequest);

    void updateScheduleRequestToSchedule(@MappingTarget Schedule schedule, UpdateScheduleRequest updateScheduleRequest);

    AddScheduleResponse scheduleToAddScheduleResponse(Schedule schedule);

    UpdateScheduleResponse scheduleToUpdateScheduleResponse(Schedule schedule);

    List<ScheduleResponse> scheduleListToScheduleResponseList(List<Schedule> scheduleList);

    ScheduleResponse scheduleToScheduleResponse(Schedule schedule);

}
