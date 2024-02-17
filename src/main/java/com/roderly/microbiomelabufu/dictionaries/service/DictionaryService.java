package com.roderly.microbiomelabufu.dictionaries.service;

import com.roderly.microbiomelabufu.dictionaries.dto.ValueLabelDTO;
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

        if(is_active){
            where += "AND is_active = 1 ";
        }

        if(!Objects.equals(whereClause, null)){
            where += whereClause;
        }

        String queryStr = "SELECT " + idColumn + ", " + descColumn + " FROM " + tableName + " t WHERE " + where;

        if(isValidQuery(queryStr)) {

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

}
