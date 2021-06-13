package org.acme.controller.repositories;

import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidCRMError;
import org.acme.model.entities.Medico;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class MedicoRepository implements PanacheRepository<Medico>{
    public Medico getMedicoById(Long id) throws InvalidCRMError{
        Medico medico = findById(id);
        if(medico!=null){
            return medico;
        } else{
            throw new InvalidCRMError();
        }
        
    }
}
