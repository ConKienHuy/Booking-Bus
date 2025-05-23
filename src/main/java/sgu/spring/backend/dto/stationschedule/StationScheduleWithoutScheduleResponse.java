package sgu.spring.backend.dto.stationschedule;

import lombok.Data;
import sgu.spring.backend.dto.station.StationResponse;
import sgu.spring.backend.enums.EPickUpType;

import java.time.LocalDateTime;

@Data
public class StationScheduleWithoutScheduleResponse {
    private int id;

    private StationResponse stationResponse;

    private LocalDateTime timeArrival;

    private EPickUpType pickUpType;
}
