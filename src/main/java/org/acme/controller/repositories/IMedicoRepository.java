package org.acme.controller.repositories;

import org.acme.model.api.errors.InvalidCRMError;
import org.acme.model.entities.Medico;

public interface IMedicoRepository {
    public Medico getMedicoById(Long id) throws InvalidCRMError;
}
