package com.logoscti.micael.assembleia.dtos;

import com.logoscti.micael.assembleia.model.Voto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {
    private Long id;
    private String associadoId;
    private boolean voto;
    private Long idSessao;

    public VotoDTO(Voto voto) {
        this.id = voto.getId();
        this.associadoId = voto.getAssociadoId();
        this.voto = voto.getVoto();
        this.idSessao = voto.getSessao().getId();
    }
}
