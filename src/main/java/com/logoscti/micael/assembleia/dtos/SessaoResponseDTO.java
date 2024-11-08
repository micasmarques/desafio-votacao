package com.logoscti.micael.assembleia.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.logoscti.micael.assembleia.model.Sessao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoResponseDTO {
    private Long id;
    private Long idPauta;
    private LocalDateTime dataInicio;
    private Long duracao;
    private List<VotoDTO> votos;


    public SessaoResponseDTO(Sessao sessao) {
        this.id = sessao.getId();
        this.idPauta = sessao.getPauta().getId();
        this.dataInicio = sessao.getDataInicio();
        this.duracao = sessao.getDuracao();
        this.votos = new ArrayList<>();
        if (sessao.getVotos() != null) {
            sessao.getVotos().forEach(voto -> votos.add(new VotoDTO(voto)));
        }
    }
}
