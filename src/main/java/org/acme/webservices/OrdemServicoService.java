package org.acme.webservices;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.controller.repositories.ExameRepository;
import org.acme.controller.repositories.MedicoRepository;
import org.acme.controller.repositories.OrdemServicoRepository;
import org.acme.controller.repositories.PacienteRepository;
import org.acme.controller.repositories.PostoColetaRepository;
import org.acme.model.api.errors.InvalidCPFError;
import org.acme.model.api.errors.InvalidCRMError;
import org.acme.model.api.errors.InvalidExameError;
import org.acme.model.api.errors.InvalidPostoColetaError;
import org.acme.model.api.errors.ResponseError;
import org.acme.model.api.requests.OrdemServicoData;
import org.acme.model.api.response.ProtocoloData;
import org.acme.model.entities.Exame;
import org.acme.model.entities.Medico;
import org.acme.model.entities.OrdemServico;
import org.acme.model.entities.Paciente;
import org.acme.model.entities.PostoColeta;

@Path("/ordem_servico")
public class OrdemServicoService {

    @Inject
    private PacienteRepository pacienteRepository;

    @Inject
    private PostoColetaRepository postoColetaRepository;

    @Inject
    private MedicoRepository medicoRepository;

    @Inject
    private ExameRepository exameRepository;

    @Inject
    private OrdemServicoRepository ordemServicoRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrdemServico(OrdemServicoData ordemServicoData) {

        try {

            LocalDate data = ordemServicoData.data;
            Paciente paciente = pacienteRepository.getPacienteById(ordemServicoData.pacienteId);
            String convenio = ordemServicoData.convenio;
            PostoColeta postoColeta = postoColetaRepository.getPostoColetaById(ordemServicoData.postoColetaId);
            Medico medico = medicoRepository.getMedicoById(ordemServicoData.medicoId);
            Set<Exame> exames = new HashSet<>();
            for (Long exameId : ordemServicoData.examesIds) {
                Exame exame = exameRepository.getExameById(exameId);
                exames.add(exame);
            }

            OrdemServico ordemServico = ordemServicoRepository.createOrdemServico(data, paciente, convenio, postoColeta,
                    medico, exames);

            return Response.status(Status.CREATED)
                    .entity(new ProtocoloData(ordemServico.id, ordemServico.computeDataEntrega())).build();
        } catch (InvalidCPFError | InvalidCRMError | InvalidPostoColetaError | InvalidExameError error) {
            return Response.status(Status.BAD_REQUEST).entity((ResponseError) error).build();
        }

    }

}
