package com.logoscti.micael.assembleia.repositories;

import com.logoscti.micael.assembleia.model.Sessao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}