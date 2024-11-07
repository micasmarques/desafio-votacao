package com.logoscti.micael.assembleia.controllers;

import com.logoscti.micael.assembleia.dtos.PautaDTO;
import com.logoscti.micael.assembleia.model.Pauta;
import com.logoscti.micael.assembleia.services.PautaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/pautas")
@AllArgsConstructor
public class PautaController {

    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody PautaDTO pautaDto) {
        Pauta pauta = pautaService.criarPauta(pautaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pauta);
    }
}