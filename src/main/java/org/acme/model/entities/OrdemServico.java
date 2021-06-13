
package org.acme.model.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class OrdemServico extends PanacheEntity{
    public LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    public Paciente paciente;

    public String convenio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posto_coleta_id")
    public PostoColeta postoColeta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    public Medico medico;

    @ManyToMany
    @JoinTable(name = "ordem_servico_exame", 
            joinColumns = { @JoinColumn(name = "ordem_servico_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "exame_id") })
    public Set<Exame> exames = new HashSet<Exame>();


    public LocalDate computeDataEntrega(){
        return this.data.plusDays(5);
    }
}
