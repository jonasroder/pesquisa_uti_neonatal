package com.roderly.microbiomelabufu.pessoa.repository;

import com.roderly.microbiomelabufu.pessoa.model.Paciente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p, CONCAT(a.caminho_arquivo, '/', a.id_arquivo,'.jpeg'), p.nome " +
            "FROM Paciente p " +
            "LEFT JOIN Arquivo a ON a.id_entidade = p.id_paciente AND a.entidade = 'paciente' " +
            "WHERE p.id_paciente = :id_paciente")

    Optional<Tuple> findPessoaWithImageProfile(@Param("id_paciente") Long id_pessoa);

}
