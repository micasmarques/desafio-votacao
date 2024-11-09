package com.logoscti.micael.assembleia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDTO {
    private Long pautaId;
    private Long duracao;
}
