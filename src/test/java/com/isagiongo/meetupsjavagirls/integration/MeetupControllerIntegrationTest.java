package com.isagiongo.meetupsjavagirls.integration;

import com.isagiongo.meetupsjavagirls.enums.TipoEventoEnum;
import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.Talk;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import com.isagiongo.meetupsjavagirls.repository.TalkRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetupControllerIntegrationTest {

    @Autowired
    private MeetupRepository meetupRepository;

    @Autowired
    private TalkRepository talkRepository;

    private Meetup meetup;

    @LocalServerPort
    private int randomPort;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomPort;
        meetupRepository.deleteAll();
        talkRepository.deleteAll();
        meetup = meetupRepository.save(criaMeetup());
        talkRepository.saveAll(criaMeetup().getTalks());
    }

    @Test
    public void deveRetornarOkAoBuscarTodosOsMeetups() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarOkAoBuscarMeetupPorEdicao() {
        Meetup meetupEsperado = criaMeetupEsperado();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups/edicao/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("edicao", equalTo(meetupEsperado.getEdicao()))
                .body("localRealizacao", equalTo(meetupEsperado.getLocalRealizacao()))
                .body("quantidadeParticipantes", equalTo(meetupEsperado.getQuantidadeParticipantes()))
                ;
    }

    @Test
    public void deveRetornarNaoEncontradoAoBuscarPorEdicaoQueNaoExiste() {

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups/edicao/6")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
        ;
    }

    @Test
    public void deveRetornarOkAoBuscarMeetupPorId() {
        Meetup meetupEsperado = criaMeetupEsperado();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups/"+ meetup.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("edicao", equalTo(meetupEsperado.getEdicao()))
                .body("localRealizacao", equalTo(meetupEsperado.getLocalRealizacao()))
                .body("quantidadeParticipantes", equalTo(meetupEsperado.getQuantidadeParticipantes()))
        ;
    }

    @Test
    public void deveRetornarNaoEncontradoAoBuscarPorIdQueNaoExiste() {

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups/5d73a3e46d04201b")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
        ;
    }

    private Meetup criaMeetup() {
        LocalDate data = LocalDate.of(2018, Month.JUNE, 18);

        Talk talk1 = new Talk();
        talk1.setId("762hgsdb2");
        talk1.setTipo(TipoEventoEnum.CODING_DOJO);
        talk1.setTitulo("Testes unitários");
        List<String> palestrantes = Arrays.asList("Marina Moreira", "Juliana Ferreira");
        talk1.setPalestrantes(palestrantes);

        Talk talk2 = new Talk();
        talk2.setId("uiuier2287");
        talk2.setTipo(TipoEventoEnum.WORKSHOP);
        talk2.setTitulo("API com Spring Boot");
        List<String> palestrantes2 = Arrays.asList("Isa Giongo", "Ana Carolina Ferreira");
        talk2.setPalestrantes(palestrantes2);

        List<Talk> talks = new ArrayList<>();
        talks.add(talk1);
        talks.add(talk2);

        Meetup meetupSalvo = new Meetup();
        meetupSalvo.setLocalRealizacao("Tecnopuc");
        meetupSalvo.setDataRealizacao(data);
        meetupSalvo.setEdicao(1);
        meetupSalvo.setTalks(talks);
        meetupSalvo.setQuantidadeParticipantes(22);

        return meetupSalvo;
    }

    private Meetup criaMeetupEsperado() {
        Meetup meetupEsperado = new Meetup();
        meetupEsperado.setEdicao(1);
        meetupEsperado.setLocalRealizacao("Tecnopuc");
        meetupEsperado.setQuantidadeParticipantes(22);

        return meetupEsperado;
    }

}
