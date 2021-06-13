package org.acme.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.controller.repositories.ExameRepository;
import org.acme.model.entities.Exame;

@Path("/exame")
public class ExameService {

    @Inject
    private ExameRepository exameRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exame> exames() {
        return exameRepository.getExames();
    }
}
