package com.logoscti.micael.assembleia.controllers;

import com.logoscti.micael.assembleia.dtos.VotoDTO;
import com.logoscti.micael.assembleia.services.VotoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/votos")
@AllArgsConstructor
public class VotoController {

    private VotoService votoService;

    @PostMapping("/{sessaoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public VotoDTO registrarVoto(@PathVariable Long sessaoId, @RequestParam String associadoId, @RequestParam Boolean voto) {
        return new VotoDTO(votoService.registrarVoto(sessaoId, associadoId, voto));
    }
}