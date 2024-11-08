package com.logoscti.micael.assembleia.dtos;

import com.logoscti.micael.assembleia.model.Pauta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;

    public PautaResponseDTO(Pauta pauta) {
        this.id = pauta.getId();
        this.titulo = pauta.getTitulo();
        this.descricao = pauta.getDescricao();
    }
}
