package sgu.spring.backend.dto.schedule;

import lombok.Data;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.model.StationSchedule;

import java.util.List;

@Data
public class UpdateScheduleResponse {
    private Long scheduleId;
    private Bus bus;
    private List<StationSchedule> stationSchedules;
}
