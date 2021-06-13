package org.acme.webservices;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.controller.repositories.IExameRepository;
import org.acme.controller.repositories.IMedicoRepository;
import org.acme.controller.repositories.IOrdemServicoRepository;
import org.acme.controller.repositories.IPacienteRepository;
import org.acme.controller.repositories.IPostoColetaRepository;
import org.acme.controller.validators.CPFValidator;
import org.acme.controller.validators.CRMValidator;
import org.acme.controller.validators.ConvenioValidator;
import org.acme.controller.validators.IDExameValidator;
import org.acme.controller.validators.IDPostoColetaValidator;
import org.acme.model.api.errors.InvalidCPFError;
import org.acme.model.api.errors.InvalidCRMError;
import org.acme.model.api.errors.InvalidConvenioError;
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
    @Named("PacienteRepository")
    private IPacienteRepository pacienteRepository;

    @Inject
    @Named("PostoColetaRepository")
    private IPostoColetaRepository postoColetaRepository;

    @Inject
    @Named("MedicoRepository")
    private IMedicoRepository medicoRepository;

    @Inject
    @Named("ExameRepository")
    private IExameRepository exameRepository;

    @Inject
    @Named("OrdemServicoRepository")
    private IOrdemServicoRepository ordemServicoRepository;

    @Inject
    private ConvenioValidator convenioValidator;

    @Inject
    private CPFValidator cpfValidator;

    @Inject
    private CRMValidator crmValidator;

    @Inject
    IDExameValidator idExameValidator;

    @Inject
    IDPostoColetaValidator idPostoColetaValidator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrdemServico(OrdemServicoData ordemServicoData) {

        try {

            LocalDate data = ordemServicoData.data;
            
            cpfValidator.validate(ordemServicoData.pacienteId);
            Paciente paciente = pacienteRepository.getPacienteById(ordemServicoData.pacienteId);
            
            convenioValidator.validate(ordemServicoData.convenio);
            String convenio = ordemServicoData.convenio;
            
            idPostoColetaValidator.validate(ordemServicoData.postoColetaId);
            PostoColeta postoColeta = postoColetaRepository.getPostoColetaById(ordemServicoData.postoColetaId);
            
            crmValidator.validate(ordemServicoData.medicoId);
            Medico medico = medicoRepository.getMedicoById(ordemServicoData.medicoId);
            
            Set<Exame> exames = new HashSet<>();

            if (ordemServicoData.examesIds.isEmpty()) {
                throw new InvalidExameError();
            } else {
                for (Long exameId : ordemServicoData.examesIds) {
                    idExameValidator.validate(exameId);
                    Exame exame = exameRepository.getExameById(exameId);
                    exames.add(exame);
                }
            }

            OrdemServico ordemServico = ordemServicoRepository.createOrdemServico(data, paciente, convenio, postoColeta,
                    medico, exames);

            return Response.status(Status.CREATED)
                    .entity(new ProtocoloData(ordemServico.id, ordemServico.computeDataEntrega())).build();

        } catch (InvalidCPFError | InvalidConvenioError | InvalidCRMError | InvalidPostoColetaError | InvalidExameError error) {
            return Response.status(Status.BAD_REQUEST).entity((ResponseError) error).build();
        }

    }

}
