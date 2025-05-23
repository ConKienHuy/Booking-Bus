package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.busseat.*;
import sgu.spring.backend.model.BusSeat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusSeatMapper {

    @Mapping(target = "bus.id", source = "busId")
    @Mapping(target = "seat.id", source = "seatId")
    BusSeat addBusSeatRequestToBusSeat(AddBusSeatRequest addBusSeatRequest);

    void updateBusSeatRequestToBusSeat(@MappingTarget BusSeat busSeat ,UpdateBusSeatRequest updateBusSeatRequest);

    AddBusSeatResponse busSeatToAddBusSeatResponse(BusSeat bus);

    UpdateBusSeatResponse busSeatToUpdateBusSeatResponse(BusSeat bus);

    List<BusSeatResponse> busSeatListToBusResponse(List<BusSeat> buses);

    @Mapping(target = "bus.busSeatList", ignore = true)
    BusSeatResponse busSeatToBusSeatResponse(BusSeat bus);

}
