
package org.acme.model.api.requests;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdemServicoData{
    public LocalDate data;
    public Long pacienteId;
    public String convenio;
    public Long postoColetaId;
    public Long medicoId;
    public ArrayList<Long> examesIds;
}
