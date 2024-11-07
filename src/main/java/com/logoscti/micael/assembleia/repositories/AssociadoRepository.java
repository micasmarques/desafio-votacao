package com.logoscti.micael.assembleia.repositories;

import com.logoscti.micael.assembleia.model.Associado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, String> {
}