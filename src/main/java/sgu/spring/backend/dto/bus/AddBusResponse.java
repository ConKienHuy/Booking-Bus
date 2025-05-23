package sgu.spring.backend.dto.bus;

import lombok.Data;

@Data
public class AddBusResponse {
    private Long id;
    private String busCode;
    private String numberPlate;
}
