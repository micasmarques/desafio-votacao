package com.logoscti.micael.assembleia.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import com.logoscti.micael.assembleia.dtos.ResultadoVotacaoDTO;
import com.logoscti.micael.assembleia.dtos.SessaoDTO;
import com.logoscti.micael.assembleia.model.Pauta;
import com.logoscti.micael.assembleia.model.Sessao;
import com.logoscti.micael.assembleia.model.Voto;
import com.logoscti.micael.assembleia.repositories.PautaRepository;
import com.logoscti.micael.assembleia.repositories.SessaoRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;

    public Sessao abrirSessao(SessaoDTO sessaoDto) {
        Pauta pauta = pautaRepository.findById(sessaoDto.getPautaId())
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada"));

        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDuracao(sessaoDto.getDuracao() != null ? sessaoDto.getDuracao() : 1L);
        return sessaoRepository.save(sessao);
    }

    public ResultadoVotacaoDTO getResultado(Long sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        Map<Boolean, Long> votosContados = sessao.getVotos().stream()
                .collect(Collectors.groupingBy(Voto::getVoto, Collectors.counting()));

        long votosSim = votosContados.getOrDefault(true, 0L);
        long votosNao = votosContados.getOrDefault(false, 0L);

        return new ResultadoVotacaoDTO(sessaoId, votosSim, votosNao);
    }
}
