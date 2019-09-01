package com.isagiongo.meetupsjavagirls.services;

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
        talkRepository.saveAll(meetup.getTalks());
        return meetupRepository.save(meetup);
    }

    public Page<Meetup> findAll(Pageable pageable) {
        return meetupRepository.findAll(pageable);
    }

    public Meetup findById(String id) throws Exception{
        return meetupRepository.findById(id).orElseThrow(()-> new Exception("Meetup não encontrado."));
    }

    public Meetup update(String id, Meetup meetup) throws Exception {
        Meetup meetupExistente = findById(id);
        meetupExistente.setDataRealizacao(meetup.getDataRealizacao());
        meetupExistente.setEdicao(meetup.getEdicao());
        meetupExistente.setTalks(meetup.getTalks());
        talkRepository.saveAll(meetup.getTalks());
        return meetupRepository.save(meetupExistente);
    }

    public void delete(String id) throws Exception {
        Meetup meetup = findById(id);
        meetupRepository.delete(meetup);
    }
}
