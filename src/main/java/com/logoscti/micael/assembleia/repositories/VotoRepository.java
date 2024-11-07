package com.logoscti.micael.assembleia.repositories;

import com.logoscti.micael.assembleia.model.Voto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
