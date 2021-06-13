package org.acme.model.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Paciente extends PanacheEntity{
    public String nome;
    public LocalDate dataNascimento;
    public String genero;
    public String endereco;
}