package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.station.StationRequest;
import sgu.spring.backend.dto.station.StationResponse;
import sgu.spring.backend.model.Station;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationResponse toDTO(Station station);

    Station toEntity(StationRequest stationRequest);

    List<StationResponse> toDTOList(List<Station> station);

    /**
     * Chuyển đổi các thuộc tính stationName, stationAdress từ StationRequest sang Station
     */
    Station updateEntity(@MappingTarget Station station, StationRequest stationRequest);
}
