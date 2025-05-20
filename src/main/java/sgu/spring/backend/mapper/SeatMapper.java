package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sgu.spring.backend.dto.seat.SeatRequest;
import sgu.spring.backend.dto.seat.SeatResponse;
import sgu.spring.backend.model.Seat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Seat toEntity(SeatRequest seat);
    SeatResponse toDTO(Seat seat);
    List<SeatResponse> toDTOList(List<Seat> seatList);

}
