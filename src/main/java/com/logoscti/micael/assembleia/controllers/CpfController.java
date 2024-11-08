package com.logoscti.micael.assembleia.controllers;

import java.util.HashMap;
import java.util.Map;

import com.logoscti.micael.assembleia.services.CpfValidationService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/cpf")
@AllArgsConstructor
public class CpfController {

    private final CpfValidationService cpfValidationService;

    @GetMapping("/{cpf}/status")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> verificarCpf(@PathVariable String cpf) {
        String status = cpfValidationService.verificarCpf(cpf);

        Map<String, String> response = new HashMap<>();
        response.put("status", status);

        return response;
    }
}
