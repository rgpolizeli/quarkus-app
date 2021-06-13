package org.acme.controller.repositories;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidExameError;
import org.acme.model.entities.Exame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
@Named("ExameRepository")
public class ExameRepository implements IExameRepository, PanacheRepository<Exame> {

    public Exame getExameById(Long id) throws InvalidExameError {
        Exame exame = findById(id);
        if (exame != null) {
            return exame;
        } else {
            throw new InvalidExameError();
        }
    }

    public List<Exame> getExames() {
        return listAll();
    }

}
