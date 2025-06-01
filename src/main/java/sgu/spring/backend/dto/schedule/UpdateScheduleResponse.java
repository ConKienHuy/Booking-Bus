package sgu.spring.backend.dto.schedule;

import lombok.Data;
import sgu.spring.backend.dto.bus.BusResponse;
import sgu.spring.backend.dto.station.StationResponse;

@Data
public class UpdateScheduleResponse {
    private Long id;

    private BusResponse bus;

    private StationResponse stationFrom;

    private StationResponse stationTo;
}
