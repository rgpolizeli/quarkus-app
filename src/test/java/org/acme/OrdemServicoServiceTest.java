package org.acme;

import org.acme.model.api.errors.InvalidCPFError;
import org.acme.model.api.errors.InvalidExameError;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@QuarkusTest
public class OrdemServicoServiceTest {

    public static final String ORDEM_SERVICO_ENDPOINT = "/ordem_servico";

    @Test
    public void whenPostValidOrdemServicoData_thenProtocoloDataShouldBeReturned() {
        
        given()
        .body("{\"data\":\"2021-10-05\",\"pacienteId\":23049,\"convenio\":\"QSaude\",\"postoColetaId\":12345,\"medicoId\":3049583,\"examesIds\":[440293,4029389]}")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .when()
        .post(ORDEM_SERVICO_ENDPOINT)
        .then()
        .statusCode(Status.CREATED.getStatusCode())
        .body("id", is(1) )
        .body("dataEntrega", is("2021-10-10"));
    }

    @Test
    public void whenPostOrdemServicoDataWithInvalidCPF_thenResponseErrorShouldBeReturned() {
        
        InvalidCPFError invalidCPFError = new InvalidCPFError();

        given()
        .body("{\"data\":\"2021-10-05\",\"pacienteId\":9999999,\"convenio\":\"QSaude\",\"postoColetaId\":12345,\"medicoId\":3049583,\"examesIds\":[440293,4029389]}")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .when()
        .post(ORDEM_SERVICO_ENDPOINT)
        .then()
        .statusCode(Status.BAD_REQUEST.getStatusCode())
        .body("errorDescription", is(invalidCPFError.errorDescription))
        .body("code", is(invalidCPFError.code));
    }

    @Test
    public void whenPostOrdemServicoDataWithEmptyCPF_thenResponseErrorShouldBeReturned() {
        
        InvalidCPFError invalidCPFError = new InvalidCPFError();

        given()
        .body("{\"data\":\"2021-10-05\",\"pacienteId\":null,\"convenio\":\"QSaude\",\"postoColetaId\":12345,\"medicoId\":3049583,\"examesIds\":[440293,4029389]}")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .when()
        .post(ORDEM_SERVICO_ENDPOINT)
        .then()
        .statusCode(Status.BAD_REQUEST.getStatusCode())
        .body("errorDescription", is(invalidCPFError.errorDescription))
        .body("code", is(invalidCPFError.code));
    }

    @Test
    public void whenPostOrdemServicoDataWithEmptyExames_thenResponseErrorShouldBeReturned() {
        
        InvalidExameError invalidExameError = new InvalidExameError();

        given()
        .body("{\"data\":\"2021-10-05\",\"pacienteId\":23049,\"convenio\":\"QSaude\",\"postoColetaId\":12345,\"medicoId\":3049583,\"examesIds\":[]}")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .when()
        .post(ORDEM_SERVICO_ENDPOINT)
        .then()
        .statusCode(Status.BAD_REQUEST.getStatusCode())
        .body("errorDescription", is(invalidExameError.errorDescription))
        .body("code", is(invalidExameError.code));
    }

}
