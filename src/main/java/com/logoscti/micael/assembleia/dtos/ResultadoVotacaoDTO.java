package com.logoscti.micael.assembleia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultadoVotacaoDTO {
    private Long sessaoId;
    private long votosSim;
    private long votosNao;
}
