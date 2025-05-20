package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.bus.BusRequest;
import sgu.spring.backend.dto.bus.BusResponse;
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

    @PostMapping("/add")
    public ResponseEntity<BusResponse> add(@RequestBody @Valid BusRequest busRequest) {
        BusResponse bus = busService.add(busRequest);
        return ResponseEntity.ok(bus);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BusResponse> update(@PathVariable("id") long id, @RequestBody @Valid BusRequest busRequest) {
        BusResponse bus = busService.update(id ,busRequest);
        return ResponseEntity.ok(bus);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BusResponse> delete(@PathVariable("id") long id) {
        busService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
