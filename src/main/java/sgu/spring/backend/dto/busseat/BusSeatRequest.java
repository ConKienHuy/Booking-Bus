package sgu.spring.backend.dto.busseat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BusSeatRequest {
    @NotBlank(message = "Số ghế không được để trống.")
    private String seatNumber;

    @NotNull(message = "Mã ghế không được để trống.")
    private Long seatId;

    @NotNull(message = "Mã bus không được để trống.")
    private Long busId;
}
