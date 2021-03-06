package com.isagiongo.meetupsjavagirls.models.dtos;

import com.isagiongo.meetupsjavagirls.models.Talk;

import java.time.LocalDate;
import java.util.List;

public class MeetupRequestDTO {

    private String id;
    private Integer edicao;
    private LocalDate dataRealizacao;
    private Integer quantidadeParticipantes;
    private String localRealizacao;
    private List<Talk> talks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }
}
