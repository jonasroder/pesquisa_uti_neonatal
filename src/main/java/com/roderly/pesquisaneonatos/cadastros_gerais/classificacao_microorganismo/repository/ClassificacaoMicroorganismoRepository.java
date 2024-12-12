package com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.classificacao_microorganismo.model.ClassificacaoMicroorganismo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificacaoMicroorganismoRepository extends JpaRepository <ClassificacaoMicroorganismo, Long> {
}
