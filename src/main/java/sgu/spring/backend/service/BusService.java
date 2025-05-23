package sgu.spring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgu.spring.backend.dto.bus.*;
import sgu.spring.backend.exception.EntityInactiveException;
import sgu.spring.backend.exception.EntityNotFoundException;
import sgu.spring.backend.mapper.BusMapper;
import sgu.spring.backend.model.Bus;
import sgu.spring.backend.repository.BusRepository;

import java.time.LocalDateTime;
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
        List<Bus> busList = busRepository.findAllActive();
        return busMapper.busListToBusResponseList(busList);
    }

    public BusResponse getById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus ID: " +id+ " not found."));

        if(bus.isEnable() == false) {
            throw new EntityInactiveException("Bus ID: " +id+ " not active.");
        }

        return busMapper.busToBusResponse(bus);
    }

    public AddBusResponse add(AddBusRequest addBusRequest) {
        Bus bus = busMapper.addBusRequestToBus(addBusRequest);

        bus.setEnable(true);
        bus.setCreatedAt(LocalDateTime.now());
        bus.setUpdatedAt(LocalDateTime.now());

        busRepository.save(bus);
        return busMapper.busToAddBusResponse(bus);
    }

    public UpdateBusResponse update(Long id , UpdateBusRequest updateBusRequest) {
        Bus existingBus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus ID: " +id+ " not found."));

        if(existingBus.isEnable() == false) {
            throw new EntityInactiveException("Bus ID: " +id+ " not active.");
        }

        busMapper.updateBusRequestToBus(existingBus, updateBusRequest);

        existingBus.setUpdatedAt(LocalDateTime.now());

        busRepository.save(existingBus);
        return busMapper.busToUpdateBusResponse(existingBus);
    }

    public BusResponse delete(Long id) {
        Bus existBus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " +id+ " not found."));

        existBus.setEnable(false);
        existBus.setUpdatedAt(LocalDateTime.now());

        busRepository.save(existBus);
        return busMapper.busToBusResponse(existBus);
    }
}
