package com.roderly.pesquisaneonatos.cadastros_gerais.sexo.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.sexo.model.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexoRepository extends JpaRepository <Sexo, Long> {
}
