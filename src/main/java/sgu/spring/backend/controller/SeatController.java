package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.seat.*;
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

    @PostMapping("/")
    public ResponseEntity<AddSeatResponse> add(@RequestBody @Valid AddSeatRequest seatRequest) {
        AddSeatResponse addSeatResponse = seatService.create(seatRequest);
        return ResponseEntity.ok(addSeatResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSeatResponse> update(@PathVariable("id") long id, @RequestBody @Valid UpdateSeatRequest seatRequest) {
        UpdateSeatResponse updateSeatResponse = seatService.update(id ,seatRequest);
        return ResponseEntity.ok(updateSeatResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SeatResponse> delete(@PathVariable long id) {
        SeatResponse seatResponse = seatService.delete(id);
        return ResponseEntity.ok(seatResponse);
    }

}
