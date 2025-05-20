package sgu.spring.backend.dto.bus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BusRequest {
    @NotBlank(message = "Tên xe không được để trống.")
    private String busCode;
    @NotBlank(message = "Biến số xe không được để trống.")
    private String numberPlate;
}
