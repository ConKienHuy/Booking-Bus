package sgu.spring.backend.dto.station;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddStationRequest {

    @NotBlank(message = "Tên trạm không được để trống.")
    private String stationName;

    @NotBlank(message = "Địa chỉ trạm không được để trống.")
    private String stationAdress;
}
