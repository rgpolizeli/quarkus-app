package org.acme;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.CoreMatchers.hasItems;

@QuarkusTest
public class ExameServiceTest {

    public static final String EXAME_ENDPOINT = "/exame";

    @Test
    public void whenGetRequestAllExames_thenAllExamesShouldBeReturned() {
        given()
          .when().get(EXAME_ENDPOINT)
          .then()
             .statusCode(200)
             .body("size()", is(3))
             .body("descricao", hasItems("Endoscopia‎", "Hemograma", "Eletrocardiograma"));
    }
}
