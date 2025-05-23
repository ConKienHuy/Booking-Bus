package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import sgu.spring.backend.dto.stationschedule.StationScheduleResponse;
import sgu.spring.backend.model.StationSchedule;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationScheduleMapper {

    List<StationScheduleResponse> toDTOList(List<StationSchedule> stationSchedules);

    StationScheduleResponse toDTO(StationSchedule stationSchedule);
}
