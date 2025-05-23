package sgu.spring.backend.dto.schedule;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddScheduleRequest {
    @NotNull(message = "Vui lòng nhập mã bus.")
    private Long busId;

    private List<Long> stationSchedules;
}
