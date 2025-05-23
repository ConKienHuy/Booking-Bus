package sgu.spring.backend.dto.busseat;

import lombok.Data;
import sgu.spring.backend.dto.seat.SeatResponse;

@Data
public class BusSeatWithoutBusResponse {
    private Long id;
    private SeatResponse seat;
    private String seatNumber;
} // Dùng trong BusMapper
