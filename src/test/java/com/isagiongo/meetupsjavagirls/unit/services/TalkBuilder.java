package com.isagiongo.meetupsjavagirls.unit.services;

import com.isagiongo.meetupsjavagirls.enums.TipoEventoEnum;
import com.isagiongo.meetupsjavagirls.models.Talk;

import java.util.Arrays;
import java.util.List;

public class TalkBuilder {

    public static final String ID1 = "762hgsdb2";
    public static final TipoEventoEnum CODING_DOJO = TipoEventoEnum.CODING_DOJO;
    public static final String TESTES_UNITÁRIOS = "Testes unitários";
    public static final List<String> PALESTRANTES = Arrays.asList("Marina Moreira", "Juliana Ferreira");
    public static final String ID2 = "uiuier2287";
    public static final TipoEventoEnum WORKSHOP = TipoEventoEnum.WORKSHOP;
    public static final String API_COM_SPRING_BOOT = "API com Spring Boot";
    public static final List<String> PALESTRANTES2 = Arrays.asList("Isa Giongo", "Ana Carolina Ferreira");

    public static List<Talk> criaTalks() {
        Talk primeiraTalk = new Talk();
        primeiraTalk.setId(ID1);
        primeiraTalk.setTipo(CODING_DOJO);
        primeiraTalk.setTitulo(TESTES_UNITÁRIOS);
        primeiraTalk.setPalestrantes(PALESTRANTES);

        Talk segundaTalk = new Talk();
        segundaTalk.setId(ID2);
        segundaTalk.setTipo(WORKSHOP);
        segundaTalk.setTitulo(API_COM_SPRING_BOOT);
        segundaTalk.setPalestrantes(PALESTRANTES2);

        return Arrays.asList(primeiraTalk, segundaTalk);
    }

}
