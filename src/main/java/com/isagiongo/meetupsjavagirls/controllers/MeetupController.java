package com.isagiongo.meetupsjavagirls.controllers;

import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import com.isagiongo.meetupsjavagirls.services.MeetupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @GetMapping("/edicao/{edicao}")
    public ResponseEntity<Meetup> buscaPorEdicao(@PathVariable Integer edicao) {
        return ResponseEntity.ok(meetupService.findByEdicao(edicao));
    }

    @PostMapping
    public ResponseEntity<Meetup> cria(@RequestBody MeetupRequestDTO meetup) {
        Meetup meetupSalvo = meetupService.create(meetup);
        return ResponseEntity.created(URI.create("/" + meetupSalvo.getId())).body(meetupSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meetup> altera(@PathVariable String id, @RequestBody MeetupRequestDTO meetup) {
        return ResponseEntity.ok(meetupService.update(id, meetup));
    }

    @DeleteMapping("/edicao/{edicao}")
    public ResponseEntity<Void> deleta(@PathVariable Integer edicao) {
        meetupService.deleteByEdition(edicao);
        return ResponseEntity.ok().build();
    }

}
