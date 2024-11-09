package com.logoscti.micael.assembleia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.logoscti.micael.assembleia.dtos.PautaDTO;
import com.logoscti.micael.assembleia.model.Pauta;
import com.logoscti.micael.assembleia.repositories.PautaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPauta_deveRetornarPautaCriada() {
        PautaDTO pautaDTO = new PautaDTO("Título Teste", "Descrição Teste");

        when(pautaRepository.save(any(Pauta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pauta pautaCriada = pautaService.criarPauta(pautaDTO);

        assertNotNull(pautaCriada);
        assertEquals("Título Teste", pautaCriada.getTitulo());
        assertEquals("Descrição Teste", pautaCriada.getDescricao());
    }
}
