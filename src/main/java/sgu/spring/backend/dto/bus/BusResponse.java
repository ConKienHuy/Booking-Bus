package sgu.spring.backend.dto.bus;

import lombok.Data;
import sgu.spring.backend.dto.busseat.BusSeatWithoutBusResponse;

import java.util.List;

@Data
public class BusResponse {
    private Long id;
    private String busCode;
    private String numberPlate;

    private List<BusSeatWithoutBusResponse> busSeatList;
}
