package org.acme.model.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Medico extends PanacheEntity{
    public String nome;
    public String especialidade;
}