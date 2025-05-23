package sgu.spring.backend.dto.station;

import lombok.Data;

@Data
public class UpdateStationResponse {

    private long id;

    private String stationName;

    private String stationAdress;
}
