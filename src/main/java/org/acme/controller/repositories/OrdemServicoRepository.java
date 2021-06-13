package org.acme.controller.repositories;

import java.time.LocalDate;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.acme.model.entities.Exame;
import org.acme.model.entities.Medico;
import org.acme.model.entities.OrdemServico;
import org.acme.model.entities.Paciente;
import org.acme.model.entities.PostoColeta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
@Named("OrdemServicoRepository")
public class OrdemServicoRepository implements IOrdemServicoRepository,PanacheRepository<OrdemServico> {

    @Transactional
    public OrdemServico createOrdemServico(LocalDate data, Paciente paciente, String convenio, PostoColeta postoColeta,
            Medico medico, Set<Exame> exames) {

        OrdemServico ordem = new OrdemServico();
        ordem.data = data;
        ordem.paciente = paciente;
        ordem.convenio = convenio;
        ordem.postoColeta = postoColeta;
        ordem.medico = medico;
        ordem.exames = exames;
        persist(ordem);

        return ordem;
    }

}
