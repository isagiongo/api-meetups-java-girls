package com.isagiongo.meetupsjavagirls.controllers;

import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import com.isagiongo.meetupsjavagirls.services.MeetupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meetups")
public class MeetupController {

    private final MeetupService meetupService;

    public MeetupController(MeetupService meetupService) {
        this.meetupService = meetupService;
    }

    @GetMapping
    public ResponseEntity<Page<Meetup>> buscaTodos(Pageable pageable) {
        return ResponseEntity.ok(meetupService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meetup> buscaPorId(@PathVariable String id) {
        return ResponseEntity.ok(meetupService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Meetup> cria(@RequestBody MeetupRequestDTO meetup) {
        return ResponseEntity.ok(meetupService.create(meetup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meetup> altera(@PathVariable String id, @RequestBody MeetupRequestDTO meetup) {
        return ResponseEntity.ok(meetupService.update(id, meetup));
    }

}
