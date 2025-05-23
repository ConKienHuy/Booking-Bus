package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.busseat.*;
import sgu.spring.backend.service.BusSeatService;

import java.util.List;

@RestController
@RequestMapping("/api/busseat")
public class BusSeatController {
    private BusSeatService busSeatService;

    @Autowired
    public BusSeatController(BusSeatService busSeatService) {
        this.busSeatService = busSeatService;
    }

    @GetMapping("")
    public ResponseEntity<List<BusSeatResponse>> getAll() {
        return ResponseEntity.ok(busSeatService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusSeatResponse> getById(@PathVariable("id") long id) {
        BusSeatResponse busSeat = busSeatService.getById(id);
        return ResponseEntity.ok(busSeat);
    }

    @PostMapping("")
    public ResponseEntity<AddBusSeatResponse> add(@RequestBody @Valid AddBusSeatRequest busSeatRequest) {
        AddBusSeatResponse busSeat = busSeatService.create(busSeatRequest);
        return ResponseEntity.ok(busSeat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateBusSeatResponse> update(@PathVariable("id") long id, @RequestBody @Valid UpdateBusSeatRequest busSeatRequest) {
        UpdateBusSeatResponse busSeat = busSeatService.update(id, busSeatRequest);
        return ResponseEntity.ok(busSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusSeatResponse> delete(@PathVariable("id") long id) {
        busSeatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}