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

    private  String where = "1 = 1 ";

    @PersistenceContext
    private EntityManager entityManager;

    public List<ValueLabelDTO> findIdAndLabelByTableAndCondition(String idColumn, String descColumn, String tableName, String whereClause, boolean situacao) {

        if(situacao){
            where += "AND id_situacao = 1 ";
        }

        if(!Objects.equals(whereClause, null)){
            where += whereClause;
        }

        String queryStr = "SELECT t." + idColumn + ", t." + descColumn + " FROM " + tableName + " t WHERE " + where;
        Query query = entityManager.createNativeQuery(queryStr);
        List<Object[]> results = query.getResultList();
        return results.stream()
                .map(result -> new ValueLabelDTO(result[0], (String) result[1]))
                .collect(Collectors.toList());
    }

}
