package com.roderly.pesquisaneonatos.neonato.repository;

import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeonatoRepository extends JpaRepository<Neonato, Long> {

    Optional<Neonato> findByProntuario(String prontuario);
}
