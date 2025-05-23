package sgu.spring.backend.dto.busseat;

import lombok.Data;
import sgu.spring.backend.dto.bus.BusResponse;
import sgu.spring.backend.dto.seat.SeatResponse;

@Data
public class AddBusSeatResponse {
    private Long id;
    private SeatResponse seat;
    private BusResponse bus;
    private String seatNumber;
}
