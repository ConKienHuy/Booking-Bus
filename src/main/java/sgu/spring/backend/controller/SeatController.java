package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.seat.SeatRequest;
import sgu.spring.backend.dto.seat.SeatResponse;
import sgu.spring.backend.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    private SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("")
    public ResponseEntity<List<SeatResponse>> getAll() {
        List<SeatResponse> seatList = seatService.getAll();
        return ResponseEntity.ok(seatList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getById(@PathVariable long id) {
        SeatResponse seat = seatService.getById(id);
        return ResponseEntity.ok(seat);
    }

    @PostMapping("/add")
    public ResponseEntity<SeatResponse> add(@RequestBody @Valid SeatRequest seatRequest) {
        SeatResponse seatResponse = seatService.save(seatRequest);
        return ResponseEntity.ok(seatResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SeatResponse> update(@PathVariable("id") long id, @RequestBody @Valid SeatRequest seatRequest) {
        SeatResponse seatResponse = seatService.save(seatRequest);
        return ResponseEntity.ok(seatResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        seatService.delete(id);
        return ResponseEntity.ok("");
    }

}
