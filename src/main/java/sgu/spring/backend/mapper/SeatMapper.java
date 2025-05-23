package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.seat.*;
import sgu.spring.backend.model.Seat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    Seat addSeatRequestToSeat(AddSeatRequest addSeatRequest);

    void updateSeatRequestToSeat(@MappingTarget Seat seat, UpdateSeatRequest updateSeatRequest);

    AddSeatResponse seatToAddSeatResponse(Seat seat);

    UpdateSeatResponse seatToUpdateSeatResponse(Seat seat);

    List<SeatResponse> seatListToSeatResponse(List<Seat> seatList);

    SeatResponse seatToSeatResponse(Seat seat);
}
