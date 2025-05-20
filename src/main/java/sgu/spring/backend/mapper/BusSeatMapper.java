package sgu.spring.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sgu.spring.backend.dto.busseat.BusSeatRequest;
import sgu.spring.backend.dto.busseat.BusSeatResponse;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.model.BusSeat;
import sgu.spring.backend.model.Seat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusSeatMapper {
    BusSeatResponse toDTO(BusSeat bus);

    /**
     * Khi ta viết:
     *     @Mapping(source  = "busId", target = "bus")
     * thì MapStruct sẽ tìm method có: <br>
     *     - Input: Long <br>
     *     - Output: Bus <br>
     * Nếu tìm thấy method này, nó sẽ tự gọi mà ta không cần làm gì cả.
     */
    @Mapping(source = "busId", target = "bus")
    @Mapping(source = "seatId", target = "seat")
    BusSeat toEntity(BusSeatRequest bus);

    List<BusSeatResponse> toDTOList(List<BusSeat> busSeats);

    /**
     * Chuyển đổi các thuộc tính seatNumber từ busRequset sang Bus
     */
    @Mapping(target = "bus", ignore = true)
    @Mapping(target = "seat", ignore = true)
    void updateEntity(@MappingTarget BusSeat busSeat, BusSeatRequest updateBusseat);

    default Bus toBusEntity(Long busId) {
        Bus busEntity = new Bus();
        busEntity.setId(busId);
        return busEntity;
    }

    default Seat toSeatEntity(Long seatId) {
        Seat seatEntity = new Seat();
        seatEntity.setId(seatId);
        return seatEntity;
    }

}
