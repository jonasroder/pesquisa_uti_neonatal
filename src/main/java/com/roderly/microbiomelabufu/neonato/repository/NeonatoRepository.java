package com.roderly.microbiomelabufu.neonato.repository;

import com.roderly.microbiomelabufu.neonato.model.Neonato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeonatoRepository extends JpaRepository<Neonato, Long> {

    Optional<Neonato> findByProntuario(String prontuario);
}
