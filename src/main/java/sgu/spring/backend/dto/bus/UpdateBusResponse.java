package sgu.spring.backend.dto.bus;

import lombok.Data;

@Data
public class UpdateBusResponse {
    private Long id;
    private String busCode;
    private String numberPlate;
}
