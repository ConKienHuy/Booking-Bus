package sgu.spring.backend.dto.stationschedule;

import lombok.Data;
import sgu.spring.backend.dto.schedule.ScheduleResponse;
import sgu.spring.backend.dto.station.StationResponse;
import sgu.spring.backend.enums.EPickUpType;

import java.time.LocalDateTime;

@Data
public class StationScheduleResponse {
    private Long id;

    private StationResponse stationResponse;

    private ScheduleResponse scheduleResponse;

    private LocalDateTime arrivalTime;

    private EPickUpType pickUpType;
}
