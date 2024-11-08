package com.logoscti.micael.assembleia.controllers;

import com.logoscti.micael.assembleia.dtos.SessaoDTO;
import com.logoscti.micael.assembleia.dtos.SessaoResponseDTO;
import com.logoscti.micael.assembleia.services.SessaoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/sessoes")
@AllArgsConstructor
@Slf4j
public class SessaoController {

    private SessaoService sessaoService;

    @PostMapping("/abrir")
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoResponseDTO abrirSessao(@RequestBody SessaoDTO sessaoDto) {
        return new SessaoResponseDTO(sessaoService.abrirSessao(sessaoDto));
    }
}