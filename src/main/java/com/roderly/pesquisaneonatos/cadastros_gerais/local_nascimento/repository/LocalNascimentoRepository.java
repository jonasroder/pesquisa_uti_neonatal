package com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.local_nascimento.model.LocalNascimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalNascimentoRepository extends JpaRepository <LocalNascimento, Long> {
}
