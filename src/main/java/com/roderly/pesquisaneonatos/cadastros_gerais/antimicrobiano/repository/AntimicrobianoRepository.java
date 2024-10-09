package com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.antimicrobiano.model.Antimicrobiano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntimicrobianoRepository extends JpaRepository <Antimicrobiano, Long> {
}
