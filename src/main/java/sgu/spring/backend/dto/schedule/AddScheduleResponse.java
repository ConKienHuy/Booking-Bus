package sgu.spring.backend.dto.schedule;

import lombok.Data;
import sgu.spring.backend.dto.bus.BusResponse;
import sgu.spring.backend.dto.stationschedule.StationScheduleWithoutScheduleResponse;

import java.util.List;

@Data
public class AddScheduleResponse {
    private Long scheduleId;
    private BusResponse bus;
    private List<StationScheduleWithoutScheduleResponse> stationSchedules;
}
