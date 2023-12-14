package com.roderly.microbiomelabufu.pessoa.repository;

import com.roderly.microbiomelabufu.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
