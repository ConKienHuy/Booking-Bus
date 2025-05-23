package sgu.spring.backend.dto.station;

import lombok.Data;

@Data
public class UpdateStationRequest {

    private String stationName;

    private String stationAdress;
}
