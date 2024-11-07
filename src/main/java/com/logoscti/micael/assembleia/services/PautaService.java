package com.logoscti.micael.assembleia.services;

import com.logoscti.micael.assembleia.dtos.PautaDTO;
import com.logoscti.micael.assembleia.model.Pauta;
import com.logoscti.micael.assembleia.repositories.PautaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PautaService {

    private PautaRepository pautaRepository;

    public Pauta criarPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaDTO.getTitulo());
        pauta.setDescricao(pautaDTO.getDescricao());
        return pautaRepository.save(pauta);
    }
}
