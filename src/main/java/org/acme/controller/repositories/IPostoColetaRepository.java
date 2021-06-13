package org.acme.controller.repositories;

import org.acme.model.api.errors.InvalidPostoColetaError;
import org.acme.model.entities.PostoColeta;

public interface IPostoColetaRepository {
    public PostoColeta getPostoColetaById(Long id) throws InvalidPostoColetaError;
}
