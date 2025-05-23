package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.bus.*;
import sgu.spring.backend.service.BusService;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    private BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("")
    public ResponseEntity<List<BusResponse>> getAll() {
        return ResponseEntity.ok(busService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusResponse> getById(@PathVariable("id") long id) {
        BusResponse bus = busService.getById(id);
        return ResponseEntity.ok(bus);
    }

    @PostMapping("/")
    public ResponseEntity<AddBusResponse> add(@RequestBody @Valid AddBusRequest addBusRequest) {
        AddBusResponse addBusResponse = busService.add(addBusRequest);
        return ResponseEntity.ok(addBusResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateBusResponse> update(@PathVariable("id") long id, @RequestBody @Valid UpdateBusRequest updateBusRequest) {
        UpdateBusResponse bus = busService.update(id, updateBusRequest);
        return ResponseEntity.ok(bus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusResponse> delete(@PathVariable("id") long id) {
        BusResponse busResponse = busService.delete(id);
        return ResponseEntity.ok(busResponse);
    }
}
