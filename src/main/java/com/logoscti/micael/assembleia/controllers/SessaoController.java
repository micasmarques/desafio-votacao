package com.logoscti.micael.assembleia.controllers;

import com.logoscti.micael.assembleia.dtos.SessaoDTO;
import com.logoscti.micael.assembleia.model.Sessao;
import com.logoscti.micael.assembleia.services.SessaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/sessoes")
@AllArgsConstructor
public class SessaoController {

    private SessaoService sessaoService;

    @PostMapping("/abrir")
    public ResponseEntity<Sessao> abrirSessao(@RequestBody SessaoDTO sessaoDto) {
        Sessao sessao = sessaoService.abrirSessao(sessaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessao);
    }
}