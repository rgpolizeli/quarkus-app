package org.acme.controller.repositories;

import java.time.LocalDate;
import java.util.Set;

import org.acme.model.entities.Exame;
import org.acme.model.entities.Medico;
import org.acme.model.entities.OrdemServico;
import org.acme.model.entities.Paciente;
import org.acme.model.entities.PostoColeta;

public interface IOrdemServicoRepository {
    public OrdemServico createOrdemServico(LocalDate data, Paciente paciente, String convenio, PostoColeta postoColeta,
            Medico medico, Set<Exame> exames);
}
