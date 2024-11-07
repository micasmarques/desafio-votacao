package com.logoscti.micael.assembleia.controllers;

import com.logoscti.micael.assembleia.model.Voto;
import com.logoscti.micael.assembleia.services.VotoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/votos")
@AllArgsConstructor
public class VotoController {

    private VotoService votoService;

    @PostMapping("/{sessaoId}")
    public ResponseEntity<Voto> registrarVoto(@PathVariable Long sessaoId, @RequestParam String associadoId, @RequestParam Boolean voto) {
        Voto novoVoto = votoService.registrarVoto(sessaoId, associadoId, voto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVoto);
    }
}