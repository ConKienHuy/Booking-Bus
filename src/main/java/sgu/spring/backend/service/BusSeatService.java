package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.busseat.BusSeatRequest;
import sgu.spring.backend.dto.busseat.BusSeatResponse;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.BusSeatMapper;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.model.BusSeat;
import sgu.spring.backend.model.Seat;
import sgu.spring.backend.repository.BusRepository;
import sgu.spring.backend.repository.BusSeatRepository;
import sgu.spring.backend.repository.SeatRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class BusSeatService {
    private BusSeatRepository busSeatRepository;
    private BusSeatMapper busSeatMapper;
    private BusRepository busRepository;
    private SeatRepository seatRepository;

    @Autowired
    public BusSeatService(BusSeatRepository busSeatRepository, BusSeatMapper busSeatMapper, BusRepository busRepository, SeatRepository seatRepository) {
        this.busSeatRepository = busSeatRepository;
        this.busSeatMapper = busSeatMapper;
        this.busRepository = busRepository;
        this.seatRepository = seatRepository;
    }

    public List<BusSeatResponse> getAll() {
        List<BusSeat> busSeatList = busSeatRepository.findAll();
        return busSeatMapper.toDTOList(busSeatList);
    }

    public BusSeatResponse getById(Long id) {
        BusSeat busSeat = busSeatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BusSeat with ID " +id+ " not found."));

        return busSeatMapper.toDTO(busSeat);
    }

    public BusSeatResponse create(BusSeatRequest busSeatRequest) {
        BusSeat busSeat = busSeatMapper.toEntity(busSeatRequest);
        busSeat.setStatus(1);
        busSeat.setCreatedAt(LocalDateTime.now());
        busSeat.setUpdatedAt(LocalDateTime.now());

        busSeat = busSeatRepository.save(busSeat);
        return busSeatMapper.toDTO(busSeat);
    }

    public BusSeatResponse update(Long id, BusSeatRequest busSeatRequest) {
        BusSeat existBusSeat = busSeatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus Seat with ID " +id+ " not found."));

        Bus bus = getBusOrThrow(busSeatRequest.getBusId());
        Seat seat = getSeatOrThrow(busSeatRequest.getSeatId());
        existBusSeat.setBus(bus);
        existBusSeat.setSeat(seat);

        busSeatMapper.updateEntity(existBusSeat, busSeatRequest);
        existBusSeat.setUpdatedAt(LocalDateTime.now());

        busSeatRepository.save(existBusSeat);
        return busSeatMapper.toDTO(existBusSeat);
    }

    public BusSeatResponse delete(Long id) {
        BusSeat existBusSeat = busSeatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus Seat with ID " +id+ " not found."));

        existBusSeat.setStatus(0);
        existBusSeat.setUpdatedAt(LocalDateTime.now());
        return busSeatMapper.toDTO(existBusSeat);
    }

    private Bus getBusOrThrow(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " +id+ " not found."));
    }

    private Seat getSeatOrThrow(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat with ID " +id+ " not found."));
    }
}
