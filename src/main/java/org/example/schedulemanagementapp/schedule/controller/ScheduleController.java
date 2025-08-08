package org.example.schedulemanagementapp.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.schedule.dto.SchedulerUpdateRequest;
import org.example.schedulemanagementapp.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleBaseResponse> save(@RequestBody ScheduleBaseRequest request) {
        return new ResponseEntity<>(scheduleService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleBaseResponse>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleBaseResponse> findScheduleById(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findScheduleById(scheduleId));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleBaseResponse> updateScheduleById(@PathVariable Long scheduleId, @RequestBody SchedulerUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.updateScheduleById(scheduleId, request));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
