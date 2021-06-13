package org.acme.controller.repositories;

import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidPostoColetaError;
import org.acme.model.entities.PostoColeta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
@Named("PostoColetaRepository")
public class PostoColetaRepository implements IPostoColetaRepository,PanacheRepository<PostoColeta> {
    public PostoColeta getPostoColetaById(Long id) throws InvalidPostoColetaError {
        PostoColeta postoColeta = findById(id);
        if (postoColeta != null) {
            return postoColeta;
        } else {
            throw new InvalidPostoColetaError();
        }

    }
}
