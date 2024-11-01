package com.roderly.pesquisaneonatos.neonato.repository;

import com.roderly.pesquisaneonatos.neonato.model.Neonato;
import com.roderly.pesquisaneonatos.neonato.model.NeonatoSitioMalformacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeonatoSitioMalformacaoRepository extends JpaRepository<NeonatoSitioMalformacao, Long> {

    void deleteByNeonato(Neonato neonato);
}


