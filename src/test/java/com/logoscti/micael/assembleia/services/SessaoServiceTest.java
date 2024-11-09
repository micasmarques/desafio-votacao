package com.logoscti.micael.assembleia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.logoscti.micael.assembleia.dtos.ResultadoVotacaoDTO;
import com.logoscti.micael.assembleia.dtos.SessaoDTO;
import com.logoscti.micael.assembleia.model.Pauta;
import com.logoscti.micael.assembleia.model.Sessao;
import com.logoscti.micael.assembleia.model.Voto;
import com.logoscti.micael.assembleia.repositories.PautaRepository;
import com.logoscti.micael.assembleia.repositories.SessaoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoService sessaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void abrirSessao_deveRetornarSessaoCriada() {
        SessaoDTO sessaoDTO = new SessaoDTO(1L, 10L);
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(Sessao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Sessao sessao = sessaoService.abrirSessao(sessaoDTO);

        assertNotNull(sessao);
        assertEquals(10L, sessao.getDuracao());
    }

    @Test
    void getResultado_deveRetornarResultadoVotacao() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);

        Voto votoSim = new Voto();
        votoSim.setVoto(true);

        Voto votoNao = new Voto();
        votoNao.setVoto(false);

        sessao.setVotos(List.of(votoSim, votoNao, votoSim));

        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));

        ResultadoVotacaoDTO resultado = sessaoService.getResultado(1L);

        assertEquals(2, resultado.getVotosSim());
        assertEquals(1, resultado.getVotosNao());
    }
}
