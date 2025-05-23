package sgu.spring.backend.dto.seat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddSeatRequest {
    @NotBlank(message = "Tên không được để trống.")
    private String seatType;

    @Min(value = 0, message = "Giá trị không được nhỏ hơn 0.")
    private double seatPrice;
}
