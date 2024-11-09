package com.logoscti.micael.assembleia.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.logoscti.micael.assembleia.exceptions.CpfInvalidoException;

import org.junit.jupiter.api.Test;

class CpfValidationServiceTest {

    private final CpfValidationService cpfValidationService = new CpfValidationService();

    @Test
    void verificarCpfValido_deveRetornarStatus() {
        String cpf = "12345678909";  // CPF de exemplo com formato válido

        String status = cpfValidationService.verificarCpf(cpf);

        assertTrue(status.equals("ABLE_TO_VOTE") || status.equals("UNABLE_TO_VOTE"));
    }

    @Test
    void verificarCpfInvalido_deveLancarExcecao() {
        String cpfInvalido = "123";

        assertThrows(CpfInvalidoException.class, () -> cpfValidationService.verificarCpf(cpfInvalido));
    }
}
