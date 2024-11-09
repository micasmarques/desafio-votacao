package com.logoscti.micael.assembleia.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.logoscti.micael.assembleia.batch.VotoProducer;
import com.logoscti.micael.assembleia.model.Sessao;
import com.logoscti.micael.assembleia.repositories.SessaoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VotoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private VotoProducer votoProducer;

    @InjectMocks
    private VotoService votoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarVoto_deveEnviarParaVotoProducer() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);

        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));

        votoService.registrarVoto(1L, "12345678909", true);

        verify(votoProducer, times(1)).enviarVotoParaFila(any());
    }
}