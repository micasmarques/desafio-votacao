package com.logoscti.micael.assembleia.services;

import java.util.Random;

import com.logoscti.micael.assembleia.exceptions.CpfInvalidoException;

import org.springframework.stereotype.Service;

@Service
public class CpfValidationService {

    public String verificarCpf(String cpf) {
        if (!isCpfValido(cpf)) {
            throw new CpfInvalidoException("CPF inválido");
        }
        return obterStatusVotacao();
    }

    private boolean isCpfValido(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        return cpf.matches("\\d{11}");
    }

    private String obterStatusVotacao() {
        return new Random().nextBoolean() ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE";
    }
}