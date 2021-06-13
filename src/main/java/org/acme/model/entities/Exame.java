package org.acme.model.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Exame extends PanacheEntity{
    public String descricao;
    public Double preco;
}