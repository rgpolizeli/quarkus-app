package org.acme.controller.repositories;

import java.util.List;

import org.acme.model.api.errors.InvalidExameError;
import org.acme.model.entities.Exame;

public interface IExameRepository {
    public Exame getExameById(Long id) throws InvalidExameError;
    public List<Exame> getExames();
}
