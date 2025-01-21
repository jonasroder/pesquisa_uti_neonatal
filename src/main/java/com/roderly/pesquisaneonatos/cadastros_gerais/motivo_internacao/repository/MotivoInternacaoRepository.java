package com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.motivo_internacao.model.MotivoInternacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoInternacaoRepository extends JpaRepository <MotivoInternacao, Long> {
}
