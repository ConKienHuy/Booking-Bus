package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.bus.*;
import sgu.spring.backend.dto.busseat.BusSeatWithoutBusResponse;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.model.BusSeat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusMapper {

    Bus addBusRequestToBus(AddBusRequest addBusRequest);

    void updateBusRequestToBus(@MappingTarget Bus bus, UpdateBusRequest updateBusRequest);

    AddBusResponse busToAddBusResponse(Bus bus);

    UpdateBusResponse busToUpdateBusResponse(Bus bus);

    @Mapping(source = "busSeat", target = "busSeatList")
    List<BusResponse> busListToBusResponseList(List<Bus> busList);

    @Mapping(source = "busSeat", target = "busSeatList")
    BusResponse busToBusResponse(Bus bus);

    List<BusSeatWithoutBusResponse> busSeatWithoutBusResponseList(List<BusSeat> busSeatList);
}
