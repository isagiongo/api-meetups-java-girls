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
}
