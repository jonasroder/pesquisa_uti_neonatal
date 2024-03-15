package com.roderly.microbiomelabufu.formulario_alimentacao.repository;

import com.roderly.microbiomelabufu.formulario_alimentacao.model.FormularioAlimentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormularioAlimentarRepository extends JpaRepository <FormularioAlimentar, Long> {
}
