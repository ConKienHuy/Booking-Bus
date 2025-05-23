package sgu.spring.backend.dto.schedule;

import lombok.Data;

import java.util.List;

@Data
public class UpdateScheduleRequest {
    private Long busId;

    private List<Long> stationSchedules;
}
