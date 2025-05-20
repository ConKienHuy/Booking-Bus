package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.station.StationRequest;
import sgu.spring.backend.dto.station.StationResponse;
import sgu.spring.backend.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/api/station")
public class StationController {
    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("")
    public ResponseEntity<List<StationResponse>> getAll() {
        List<StationResponse> stationList = stationService.getAll();
        return ResponseEntity.ok(stationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponse> getById(@PathVariable long id) {
        StationResponse station = stationService.getById(id);
        return ResponseEntity.ok(station);
    }

    @PostMapping("/add")
    public ResponseEntity<StationResponse> add(@RequestBody @Valid StationRequest stationRequest) {
        StationResponse stationResponse = stationService.create(stationRequest);
        return ResponseEntity.ok(stationResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StationResponse> update(@PathVariable("id") long id, @RequestBody @Valid StationRequest stationRequest) {
        StationResponse stationResponse = stationService.update(id ,stationRequest);
        return ResponseEntity.ok(stationResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        stationService.delete(id);
        return ResponseEntity.ok("");
    }
}