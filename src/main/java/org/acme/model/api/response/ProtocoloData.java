package org.acme.model.api.response;

import java.time.LocalDate;

public class ProtocoloData {
    public Long id;
    public LocalDate dataEntrega;

    public ProtocoloData(Long id, LocalDate dataEntrega){
        this.id = id;
        this.dataEntrega = dataEntrega;
    }

}
