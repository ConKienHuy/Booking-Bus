package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.busseat.*;
import sgu.spring.backend.exception.EntityInactiveException;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.BusSeatMapper;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.model.BusSeat;
import sgu.spring.backend.model.Seat;
import sgu.spring.backend.repository.BusRepository;
import sgu.spring.backend.repository.BusSeatRepository;
import sgu.spring.backend.repository.SeatRepository;

import java.time.LocalDateTime;
import java.util.List;

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
        List<BusSeat> busSeatList = busSeatRepository.findAllActive();
        return busSeatMapper.busSeatListToBusResponse(busSeatList);
    }

    public BusSeatResponse getById(Long id) {
        BusSeat busSeat = busSeatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BusSeat with ID " +id+ " not found."));

        if(busSeat.isEnable() == false) {
            throw new EntityInactiveException("BusSeat with ID " + id + " not active.");
        }

        return busSeatMapper.busSeatToBusSeatResponse(busSeat);
    }

    public AddBusSeatResponse create(AddBusSeatRequest addBusSeatRequest) {
        BusSeat busSeat = busSeatMapper.addBusSeatRequestToBusSeat(addBusSeatRequest);
        busSeat.setEnable(true);
        busSeat.setCreatedAt(LocalDateTime.now());
        busSeat.setUpdatedAt(LocalDateTime.now());

        busSeat = busSeatRepository.save(busSeat);
        return busSeatMapper.busSeatToAddBusSeatResponse(busSeat);
    }

    public UpdateBusSeatResponse update(Long id, UpdateBusSeatRequest updateBusSeatRequest) {
        BusSeat existBusSeat = busSeatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus Seat with ID " +id+ " not found."));

        if(existBusSeat.isEnable() == false) {
            throw new EntityInactiveException("BusSeat with ID " + id + " not active.");
        }

        busSeatMapper.updateBusSeatRequestToBusSeat(existBusSeat, updateBusSeatRequest);
        Bus bus = getBusById(updateBusSeatRequest.getBusId());
        existBusSeat.setBus(bus);
        Seat seat = getSeatById(updateBusSeatRequest.getSeatId());
        existBusSeat.setSeat(seat);
        existBusSeat.setUpdatedAt(LocalDateTime.now());

        busSeatRepository.save(existBusSeat);
        return busSeatMapper.busSeatToUpdateBusSeatResponse(existBusSeat);
    }

    public BusSeatResponse delete(Long id) {
        BusSeat existBusSeat = busSeatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus Seat with ID " +id+ " not found."));

        existBusSeat.setEnable(false);
        existBusSeat.setUpdatedAt(LocalDateTime.now());
        return busSeatMapper.busSeatToBusSeatResponse(existBusSeat);
    }

    private Bus getBusById(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " +busId+ " not found."));
        return bus;
    }

    private Seat getSeatById(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new EntityNotFoundException("Seat with ID " +seatId+ " not found."));
        return seat;
    }

}
