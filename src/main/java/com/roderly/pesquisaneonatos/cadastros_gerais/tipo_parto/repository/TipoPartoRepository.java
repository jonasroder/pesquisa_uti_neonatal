package com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.repository;

import com.roderly.pesquisaneonatos.cadastros_gerais.tipo_parto.model.TipoParto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPartoRepository extends JpaRepository <TipoParto, Long> {
}
