package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.station.*;
import sgu.spring.backend.model.Station;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {

    Station addStationRequestToStation(AddStationRequest addStationRequest);

    void updateStationRequestToStation(@MappingTarget Station station, UpdateStationRequest updateStationRequest);

    AddStationResponse stationToAddStationResponse(Station station);

    UpdateStationResponse stationToUpdateStationResponse(Station station);

    List<StationResponse> stationListToStationResponseList(List<Station> stationList);

    StationResponse stationToStationResponse(Station station);
}
