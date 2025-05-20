package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.seat.SeatRequest;
import sgu.spring.backend.dto.seat.SeatResponse;
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
        List<Seat> seatList = seatRepository.findAll();
        return seatMapper.toDTOList(seatList);
    }

    public SeatResponse getById(Long id) {
        Seat seat = seatRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Seat with ID "+ id + " not found"));

        return seatMapper.toDTO(seat);
    }

    public SeatResponse save(SeatRequest seatRequest) {
        Seat seat = seatMapper.toEntity(seatRequest);
        seatRepository.save(seat);
        return seatMapper.toDTO(seat);
    }

    public SeatResponse delete(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat with ID "+ id + " not found"));

        seat.setStatus(0);
        seat.setUpdatedAt(LocalDateTime.now());
        return seatMapper.toDTO(seat);
    }
}
