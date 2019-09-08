package com.isagiongo.meetupsjavagirls.models;

import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Meetup {

    @Id
    private String id;
    private Integer edicao;
    private LocalDate dataRealizacao;
    private Integer quantidadeParticipantes;
    private String localRealizacao;

    @DBRef
    private List<Talk> talks;

    public Meetup(MeetupRequestDTO meetupRequestDTO){
        this.edicao = meetupRequestDTO.getEdicao();
        this.dataRealizacao = meetupRequestDTO.getDataRealizacao();
        this.quantidadeParticipantes = meetupRequestDTO.getQuantidadeParticipantes();
        this.localRealizacao = meetupRequestDTO.getLocalRealizacao();
        this.talks = meetupRequestDTO.getTalks();
    }

    public Meetup(){

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public Integer getQuantidadeParticipantes() {
        return quantidadeParticipantes;
    }

    public void setQuantidadeParticipantes(Integer quantidadeParticipantes) {
        this.quantidadeParticipantes = quantidadeParticipantes;
    }

    public String getLocalRealizacao() {
        return localRealizacao;
    }

    public void setLocalRealizacao(String localRealizacao) {
        this.localRealizacao = localRealizacao;
    }
}
