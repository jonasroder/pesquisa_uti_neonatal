package com.roderly.microbiomelabufu.pessoa.repository;

import com.roderly.microbiomelabufu.arquivo.model.Arquivo;
import com.roderly.microbiomelabufu.pessoa.dto.response.PessoaCompletoResponse;
import com.roderly.microbiomelabufu.pessoa.model.Pessoa;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p, CONCAT(a.caminho_arquivo, '/', a.id_arquivo,'.jpeg'), p.nome " +
            "FROM Pessoa p " +
            "LEFT JOIN Arquivo a ON a.id_entidade = p.id_pessoa AND a.entidade = 'pessoa' " +
            "WHERE p.id_pessoa = :id_pessoa")
    Optional<Tuple> findPessoaWithImageProfile(@Param("id_pessoa") Long id_pessoa);

}
