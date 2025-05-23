package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.seat.*;
import sgu.spring.backend.exception.EntityInactiveException;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.SeatMapper;
import sgu.spring.backend.model.Seat;
import sgu.spring.backend.repository.SeatRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatService {
    private SeatRepository seatRepository;
    private SeatMapper seatMapper;

    @Autowired
    public SeatService(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    public List<SeatResponse> getAll() {
        List<Seat> seatList = seatRepository.findAllActive();
        return seatMapper.seatListToSeatResponse(seatList);
    }

    public SeatResponse getById(Long id) {
        Seat seat = seatRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Seat ID: "+ id + " not found"));

        if (seat.isEnable() == false) {
            throw new EntityInactiveException("Seat ID: "+ id + " not active");
        }

        return seatMapper.seatToSeatResponse(seat);
    }

    public AddSeatResponse create(AddSeatRequest addSeatRequest) {
        Seat seat = seatMapper.addSeatRequestToSeat(addSeatRequest);

        seat.setEnable(true);
        seat.setCreatedAt(LocalDateTime.now());
        seat.setUpdatedAt(LocalDateTime.now());

        seatRepository.save(seat);
        return seatMapper.seatToAddSeatResponse(seat);
    }

    public UpdateSeatResponse update(Long id, UpdateSeatRequest updateSeatRequest) {
        Seat exsitingSeat = seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat ID: "+ id + " not found"));

        if (exsitingSeat.isEnable() == false) {
            throw new EntityInactiveException("Seat ID: "+ id + " not active");
        }

        seatMapper.updateSeatRequestToSeat(exsitingSeat, updateSeatRequest);
        exsitingSeat.setUpdatedAt(LocalDateTime.now());

        seatRepository.save(exsitingSeat);
        return seatMapper.seatToUpdateSeatResponse(exsitingSeat);
    }

    public SeatResponse delete(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat ID: "+ id + " not active"));

        seat.setEnable(false);
        seat.setUpdatedAt(LocalDateTime.now());
        return seatMapper.seatToSeatResponse(seat);
    }
}
