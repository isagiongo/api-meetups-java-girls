package com.isagiongo.meetupsjavagirls.services;

import com.isagiongo.meetupsjavagirls.exceptions.MeetupEditionAlreadyExists;
import com.isagiongo.meetupsjavagirls.exceptions.MeetupNotFoundException;
import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import com.isagiongo.meetupsjavagirls.repository.TalkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MeetupService {

    private final MeetupRepository meetupRepository;
    private final TalkRepository talkRepository;

    public MeetupService(MeetupRepository meetupRepository, TalkRepository talkRepository) {
        this.meetupRepository = meetupRepository;
        this.talkRepository = talkRepository;
    }

    public Meetup create(Meetup meetup) {
        Meetup meetupExistente = meetupRepository.findByEdicao(meetup.getEdicao());
        if(meetupExistente != null) {
            throw new MeetupEditionAlreadyExists("Essa edição do meetup já está cadastrada.");
        } else {
            talkRepository.saveAll(meetup.getTalks());
            return meetupRepository.save(meetup);
        }
    }

    public Page<Meetup> findAll(Pageable pageable) {
        return meetupRepository.findAll(pageable);
    }

    public Meetup findById(String id) {
        return meetupRepository.findById(id).orElseThrow(()-> new MeetupNotFoundException("Meetup não encontrado."));
    }

    public Meetup update(String id, Meetup meetup) {
        Meetup meetupExistente = findById(id);
        meetupExistente.setDataRealizacao(meetup.getDataRealizacao());
        meetupExistente.setEdicao(meetup.getEdicao());
        meetupExistente.setTalks(meetup.getTalks());
        talkRepository.saveAll(meetup.getTalks());
        return meetupRepository.save(meetupExistente);
    }

    public void delete(String id) {
        Meetup meetup = findById(id);
        meetupRepository.delete(meetup);
    }
}
