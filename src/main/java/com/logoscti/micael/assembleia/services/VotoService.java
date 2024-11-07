package com.logoscti.micael.assembleia.services;

import com.logoscti.micael.assembleia.model.Sessao;
import com.logoscti.micael.assembleia.model.Voto;
import com.logoscti.micael.assembleia.repositories.SessaoRepository;
import com.logoscti.micael.assembleia.repositories.VotoRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class VotoService {

    private VotoRepository votoRepository;
    private SessaoRepository sessaoRepository;

    public Voto registrarVoto(Long sessaoId, String associadoId, Boolean voto) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        Voto novoVoto = new Voto();
        novoVoto.setAssociadoId(associadoId);
        novoVoto.setVoto(voto);
        novoVoto.setSessao(sessao);

        return votoRepository.save(novoVoto);
    }
}
