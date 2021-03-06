package com.isagiongo.meetupsjavagirls.services;

import com.isagiongo.meetupsjavagirls.exceptions.MeetupEditionAlreadyExists;
import com.isagiongo.meetupsjavagirls.exceptions.MeetupNotFoundException;
import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import com.isagiongo.meetupsjavagirls.repository.TalkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetupService {

    private final MeetupRepository meetupRepository;
    private final TalkRepository talkRepository;

    public MeetupService(MeetupRepository meetupRepository, TalkRepository talkRepository) {
        this.meetupRepository = meetupRepository;
        this.talkRepository = talkRepository;
    }

    public Meetup create(MeetupRequestDTO meetupRequestDTO) {
        Meetup meetup = new Meetup(meetupRequestDTO);
        Optional<Meetup> meetupExistente = meetupRepository.findByEdicao(meetupRequestDTO.getEdicao());
        if(!meetupExistente.isPresent()) {
            talkRepository.saveAll(meetup.getTalks());
            return meetupRepository.save(meetup);
        } else {
            throw new MeetupEditionAlreadyExists("Essa edição do meetup já está cadastrada.");
        }
    }

    public Page<Meetup> findAll(Pageable pageable) {
        return meetupRepository.findAll(pageable);
    }

    public Meetup findById(String id) {
        return meetupRepository.findById(id).orElseThrow(()-> new MeetupNotFoundException("Meetup não encontrado."));
    }

    public Meetup findByEdicao(Integer edicao) {
        return meetupRepository.findByEdicao(edicao).orElseThrow(()-> new MeetupNotFoundException("Meetup não encontrado."));
    }

    public Meetup update(String id, MeetupRequestDTO meetupRequestDTO) {
        Meetup meetup = new Meetup(meetupRequestDTO);
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

    public void deleteByEdition(Integer edicao) {
        Meetup meetup = findByEdicao(edicao);
        meetupRepository.deleteByEdicao(meetup.getEdicao());
    }
}
