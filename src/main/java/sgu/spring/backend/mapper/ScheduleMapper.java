package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.schedule.*;
import sgu.spring.backend.dto.stationschedule.StationScheduleWithoutScheduleResponse;
import sgu.spring.backend.model.Schedule;

import java.util.List;

@Mapper(componentModel = "spring", uses = BusMapper.class)
public interface ScheduleMapper {

    @Mapping(source = "busId", target = "bus.id")
    Schedule addScheduleRequestToSchedule(AddScheduleRequest addScheduleRequest);

    void updateScheduleRequestToSchedule(@MappingTarget Schedule schedule, UpdateScheduleRequest updateScheduleRequest);

    AddScheduleResponse scheduleToAddScheduleResponse(Schedule schedule);

    UpdateScheduleResponse scheduleToUpdateScheduleResponse(Schedule schedule);

    @Mapping(target = "scheduleList", source = "stationSchedules")
    List<ScheduleResponse> scheduleListToScheduleResponseList(List<Schedule> scheduleList);

    ScheduleResponse scheduleToScheduleResponse(Schedule schedule);

    List<StationScheduleWithoutScheduleResponse> scheduleWithoutScheduleResponseList(List<StationScheduleWithoutScheduleResponse> scheduleWithoutScheduleResponseList);
}
