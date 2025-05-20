package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.bus.BusRequest;
import sgu.spring.backend.dto.bus.BusResponse;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.BusMapper;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.repository.BusRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BusService {
    private BusRepository busRepository;
    private BusMapper busMapper;

    @Autowired
    public BusService(BusRepository busRepository, BusMapper busMapper) {
        this.busRepository = busRepository;
        this.busMapper = busMapper;
    }

    public List<BusResponse> getAll() {
        List<Bus> busList = busRepository.findAll();
        return busMapper.toDTOList(busList);
    }

    public BusResponse getById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " +id+ " not found."));

        return busMapper.toDTO(bus);
    }

    public BusResponse add(BusRequest busRequest) {
        Bus bus = busMapper.toEntity(busRequest);
        bus.setStatus(1);
        bus.setCreatedAt(LocalDateTime.now());
        bus.setUpdatedAt(LocalDateTime.now());

        busRepository.save(bus);
        return busMapper.toDTO(bus);
    }

    public BusResponse update(Long id , BusRequest busRequest) {
        Bus existBus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " +id+ " not found."));

        existBus.setBusCode(busRequest.getBusCode());
        existBus.setNumberPlate(busRequest.getNumberPlate());
        existBus.setStatus(1);
        existBus.setCreatedAt(LocalDateTime.now());
        existBus.setUpdatedAt(LocalDateTime.now());


        busRepository.save(existBus);
        return busMapper.toDTO(existBus);
    }

    public BusResponse delete(Long id) {
        Bus existBus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " +id+ " not found."));

        existBus.setStatus(0);
        existBus.setUpdatedAt(LocalDateTime.now());
        return busMapper.toDTO(existBus);
    }
}
