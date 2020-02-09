package com.isagiongo.meetupsjavagirls.unit.services;

import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;

import java.time.LocalDate;
import java.time.Month;

public class MeetupBuilder {

    public static final String LOCAL = "Global Tecnopuc";
    public static final LocalDate DATA = LocalDate.of(2018, Month.JUNE, 18);
    public static final int EDICAO = 1;
    public static final int QUANTIDADE = 22;
    public static final String ID = "dfefrererer";

    public static MeetupRequestDTO criaMeetupComTalks() {
        MeetupRequestDTO meetup = new MeetupRequestDTO();
        meetup.setId(ID);
        meetup.setEdicao(EDICAO);
        meetup.setLocalRealizacao(LOCAL);
        meetup.setDataRealizacao(DATA);
        meetup.setQuantidadeParticipantes(QUANTIDADE);
        meetup.setTalks(TalkBuilder.criaTalks());
        return meetup;
    }

    public static Meetup criaMeetupRetorno() {
        Meetup meetup = new Meetup();
        meetup.setId(ID);
        meetup.setEdicao(EDICAO);
        meetup.setLocalRealizacao(LOCAL);
        meetup.setDataRealizacao(DATA);
        meetup.setQuantidadeParticipantes(QUANTIDADE);
        meetup.setTalks(TalkBuilder.criaTalks());
        return meetup;
    }

}
