package com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.peso_nascimento.model.PesoNascimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesoNascimentoRepository extends JpaRepository <PesoNascimento, Long> {
}
