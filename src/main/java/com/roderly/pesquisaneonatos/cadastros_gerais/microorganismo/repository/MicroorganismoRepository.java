package com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.microorganismo.model.Microorganismo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MicroorganismoRepository extends JpaRepository <Microorganismo, Long> {
}
