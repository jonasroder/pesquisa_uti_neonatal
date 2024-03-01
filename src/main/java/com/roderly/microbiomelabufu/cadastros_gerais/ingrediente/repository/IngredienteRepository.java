package com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.repository;

import com.roderly.microbiomelabufu.cadastros_gerais.ingrediente.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
