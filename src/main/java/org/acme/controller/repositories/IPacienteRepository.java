package org.acme.controller.repositories;

import org.acme.model.api.errors.InvalidCPFError;
import org.acme.model.entities.Paciente;

public interface IPacienteRepository {
    public Paciente getPacienteById(Long id) throws InvalidCPFError;
}
