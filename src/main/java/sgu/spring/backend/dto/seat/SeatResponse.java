package sgu.spring.backend.dto.seat;

import lombok.Data;

@Data
public class SeatResponse {
    private Long id;
    private String seatType;
    private double seatPrice;
}
