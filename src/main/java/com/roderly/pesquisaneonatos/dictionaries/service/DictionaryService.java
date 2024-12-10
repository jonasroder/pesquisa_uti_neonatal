package com.roderly.pesquisaneonatos.dictionaries.service;

import com.roderly.pesquisaneonatos.dictionaries.dto.TabelaCodigoDTO;
import com.roderly.pesquisaneonatos.dictionaries.dto.ValueLabelDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ValueLabelDTO> findIdAndLabelByTableAndCondition(String idColumn, String descColumn, String tableName, String whereClause, boolean is_active) {
        String where = "1 = 1 ";

        if (is_active) {
            where += "AND is_active = 1 ";
        }

        if (whereClause != null && !whereClause.isBlank()) {
            where += " AND " + whereClause;
        }

        // Substituir a geração da query para respeitar possíveis expressões no descColumn
        String queryStr = String.format("SELECT %s AS id, %s AS label FROM %s t WHERE %s ORDER BY label ASC",
                idColumn, descColumn, tableName, where);

        if (isValidQuery(queryStr)) {
            Query query = entityManager.createNativeQuery(queryStr);
            List<Object[]> results = query.getResultList();
            return results.stream()
                    .map(result -> new ValueLabelDTO((Long) result[0], (String) result[1]))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Consulta SQL inválida");
        }
    }



    private boolean isValidQuery(String sql) {
        String upperCaseSql = sql.trim().toUpperCase();

        if (upperCaseSql.startsWith("SELECT")) {
            return !containsDangerousKeywords(upperCaseSql);
        }
        return false;
    }


    private boolean containsDangerousKeywords(String sql) {
        String[] dangerousKeywords = {"DELETE", "UPDATE", "DROP", "TRUNCATE", "INSERT", "MERGE"};

        for (String keyword : dangerousKeywords) {
            if (sql.contains(keyword)) {
                return true;
            }
        }

        return false;
    }


    public TabelaCodigoDTO buscarCodigoValido(TabelaCodigoDTO tabela) {
        String tableName = tabela.tabela();
        Long inputCodigo = tabela.codigo();

        // SQL para verificar o maior código existente na tabela
        String queryStr = "SELECT MAX(codigo) FROM " + tableName;

        if (isValidQuery(queryStr)) {
            Query query = entityManager.createNativeQuery(queryStr);
            Long maxCodigo = (query.getSingleResult() != null) ? ((Number) query.getSingleResult()).longValue() : 0;

            boolean codigoExistente = false;

            // Verifica se o código informado já existe
            if (inputCodigo != null) {
                String checkCodigoQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE codigo = :codigo";

                if (isValidQuery(checkCodigoQuery)) {
                    Query checkQuery = entityManager.createNativeQuery(checkCodigoQuery);
                    checkQuery.setParameter("codigo", inputCodigo);

                    codigoExistente = ((Number) checkQuery.getSingleResult()).intValue() > 0;
                }
            }

            long novoCodigo;
            if (inputCodigo == null) {
                novoCodigo = maxCodigo + 1;
            } else {
                novoCodigo = codigoExistente ? maxCodigo + 1 : inputCodigo;
            }

            return new TabelaCodigoDTO(novoCodigo, tableName, codigoExistente);
        } else {
            throw new IllegalArgumentException("Consulta SQL inválida");
        }
    }

}
