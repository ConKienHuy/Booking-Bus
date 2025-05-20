package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import sgu.spring.backend.dto.bus.BusRequest;
import sgu.spring.backend.dto.bus.BusResponse;
import sgu.spring.backend.model.Bus;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusMapper {
    BusResponse toDTO(Bus bus);
    Bus toEntity(BusRequest bus);
    List<BusResponse> toDTOList(List<Bus> buseList);
}
