package com.isagiongo.meetupsjavagirls.integration;

import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import com.isagiongo.meetupsjavagirls.repository.TalkRepository;
import com.isagiongo.meetupsjavagirls.unit.services.MeetupBuilder;
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

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetupControllerIntegrationTest {

    @Autowired
    private MeetupRepository meetupRepository;

    @Autowired
    private TalkRepository talkRepository;

    private Meetup meetup;

    private Meetup meetupEsperado;

    private MeetupRequestDTO meetupDTO;

    @LocalServerPort
    private int randomPort;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomPort;
        meetupRepository.deleteAll();
        talkRepository.deleteAll();
        meetupDTO = MeetupBuilder.criaMeetupComTalks();
        meetup = new Meetup(meetupDTO);
        meetupRepository.save(meetup);
        talkRepository.saveAll(meetup.getTalks());
        meetupEsperado = MeetupBuilder.criaMeetupRetorno();
    }

    @Test
    public void deveRetornarCreatedAoInserirNovoMeetup() {
        meetupRepository.deleteAll();
        RestAssured
                .given()
                    .body(meetupDTO)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/api/v1/meetups")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("edicao", equalTo(meetupEsperado.getEdicao()))
                    .body("localRealizacao", equalTo(meetupEsperado.getLocalRealizacao()))
                    .body("quantidadeParticipantes", equalTo(meetupEsperado.getQuantidadeParticipantes()));
    }

    @Test
    public void deveRetornarOkAoBuscarTodosOsMeetups() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/v1/meetups")
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarOkAoBuscarMeetupPorEdicao() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/v1/meetups/edicao/1")
                .then()
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
                .when()
                    .get("/api/v1/meetups/edicao/6")
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value())
        ;
    }

    @Test
    public void deveRetornarOkAoBuscarMeetupPorId() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/api/v1/meetups/"+ meetup.getId())
                .then()
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
                .when()
                    .get("/api/v1/meetups/5d73a3e46d04201b")
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value())
        ;
    }

    @Test
    public void deveRetornarOkAoDeletarMeetup(){
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .delete("/api/v1/meetups/edicao/1")
                .then()
                    .statusCode(HttpStatus.OK.value())
        ;
    }

    @Test
    public void deveRetornarNoContentAoTentarDeletarMeetupNaoExistente(){
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .delete("/api/v1/meetups/edicao/15")
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value())
        ;
    }

}