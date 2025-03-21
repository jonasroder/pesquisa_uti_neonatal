package com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.idade_gestacional.model.IdadeGestacional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdadeGestacionalRepository extends JpaRepository <IdadeGestacional, Long> {
}
