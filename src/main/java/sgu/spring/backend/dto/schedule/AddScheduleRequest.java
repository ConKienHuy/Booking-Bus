package sgu.spring.backend.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddScheduleRequest {
    @NotNull(message = "Vui lòng nhập mã bus.")
    private Long busId;

    private Long stationFromId;

    private Long stationToId;
}
