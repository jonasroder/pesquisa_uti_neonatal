package com.roderly.pesquisaneonatos.common.persistense;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class EntitySpecs {

    //Filtros

    public static <T extends EntidadeRastreada> Specification<T> active() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("isActive"));
    }

    public static <T extends EntidadeRastreada> Specification<T> inactive() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isFalse(root.get("isActive"));
    }



    //Utilitarios

    public static Sort orderBy(String direction, String property) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return Sort.by(sortDirection, property);
    }
}
