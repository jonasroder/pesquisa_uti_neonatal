package com.roderly.pesquisaneonatos.common.persistense;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class FilterService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void applyIsActiveFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("isActiveFilter").setParameter("is_active", true);
    }
}
