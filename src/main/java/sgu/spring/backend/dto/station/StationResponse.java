package sgu.spring.backend.dto.station;

import lombok.Data;

@Data
public class StationResponse {
    private long id;

    private String stationName;

    private String stationAdress;
}
