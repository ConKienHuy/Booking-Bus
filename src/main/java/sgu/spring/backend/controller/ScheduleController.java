package sgu.spring.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.spring.backend.dto.schedule.*;
import sgu.spring.backend.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("")
    public ResponseEntity<List<ScheduleResponse>> getAll() {
        List<ScheduleResponse> scheduleResponseList = scheduleService.getAll();
        return ResponseEntity.ok(scheduleResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable long id) {
        ScheduleResponse scheduleResponse = scheduleService.getById(id);
        return ResponseEntity.ok(scheduleResponse);
    }

    @PostMapping("")
    public ResponseEntity<AddScheduleResponse> add(@RequestBody @Valid AddScheduleRequest addScheduleRequest) {
        AddScheduleResponse addScheduleResponse = scheduleService.create(addScheduleRequest);
        return ResponseEntity.ok(addScheduleResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponse> update(@PathVariable long id, @RequestBody @Valid UpdateScheduleRequest updateScheduleRequest) {
        UpdateScheduleResponse updateScheduleResponse = scheduleService.update(id, updateScheduleRequest);
        return ResponseEntity.ok(updateScheduleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponse> delete(@PathVariable long id) {
        ScheduleResponse scheduleResponse = scheduleService.delete(id);
        return ResponseEntity.ok(scheduleResponse);
    }
}
