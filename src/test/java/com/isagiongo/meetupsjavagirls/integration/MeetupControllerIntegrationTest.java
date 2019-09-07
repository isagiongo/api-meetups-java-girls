package com.isagiongo.meetupsjavagirls.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetupControllerIntegrationTest {

    @LocalServerPort
    private int randomPort;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomPort;
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
        String idEsperado = "5d73a3e46d04201bf6318a7a";
        String localEsperado = "Global Tecnopuc";
        Integer edicaoEsperada = 8;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups/edicao/8")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(idEsperado))
                .body("edicao", equalTo(edicaoEsperada))
                .body("localRealizacao", equalTo(localEsperado))
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
        String idEsperado = "5d73a3e46d04201bf6318a7a";
        String localEsperado = "Global Tecnopuc";
        Integer edicaoEsperada = 8;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v1/meetups/5d73a3e46d04201bf6318a7a")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(idEsperado))
                .body("edicao", equalTo(edicaoEsperada))
                .body("localRealizacao", equalTo(localEsperado))
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

}
