package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.station.*;
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

    @PostMapping("/")
    public ResponseEntity<AddStationResponse> add(@RequestBody @Valid AddStationRequest stationRequest) {
        AddStationResponse addStationResponse = stationService.create(stationRequest);
        return ResponseEntity.ok(addStationResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStationResponse> update(@PathVariable("id") long id, @RequestBody @Valid UpdateStationRequest stationRequest) {
        UpdateStationResponse updateStationResponse = stationService.update(id, stationRequest);
        return ResponseEntity.ok(updateStationResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StationResponse> delete(@PathVariable long id) {
        StationResponse stationResponse = stationService.delete(id);
        return ResponseEntity.ok(stationResponse);
    }
}