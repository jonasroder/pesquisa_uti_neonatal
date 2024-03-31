package com.roderly.microbiomelabufu.paciente.repository;

import com.roderly.microbiomelabufu.paciente.model.Paciente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p " +
            "WHERE p.id_paciente = :id_paciente")
    Optional<Tuple> findPessoaWithImageProfile(@Param("id_paciente") Long id_pessoa);


    Optional<Paciente> findByCpf(String cpf);
}
