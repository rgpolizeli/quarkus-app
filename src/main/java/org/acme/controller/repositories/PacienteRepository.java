package org.acme.controller.repositories;

import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidCPFError;
import org.acme.model.entities.Paciente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
@Singleton
public class PacienteRepository implements PanacheRepository<Paciente>{
    
    public Paciente getPacienteById(Long id) throws InvalidCPFError{
        Paciente paciente = findById(id);
        if(paciente != null){
            return paciente;
        } else{
            throw new InvalidCPFError();
        }
    }
}
