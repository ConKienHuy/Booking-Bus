package sgu.spring.backend.dto.schedule;

import lombok.Data;
import sgu.spring.backend.dto.bus.BusResponse;
import sgu.spring.backend.dto.stationschedule.StationScheduleWithoutScheduleResponse;

import java.util.List;

@Data
public class ScheduleResponse {
    private Long id;
    private BusResponse bus;
    private List<StationScheduleWithoutScheduleResponse> stationSchedules;
}
