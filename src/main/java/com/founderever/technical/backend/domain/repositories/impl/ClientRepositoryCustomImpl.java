package com.founderever.technical.backend.domain.repositories.impl;

import com.founderever.technical.backend.domain.entities.Client;
import com.founderever.technical.backend.domain.repositories.ClientRepositoryCustom;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Pagination<Client> findAllClients(Pageable pageable) {
        StringBuilder queryBuilder = new StringBuilder(" select client from Client client ");
        StringBuilder countBuilder = new StringBuilder(" select count(client) from Client client ");

        jakarta.persistence.Query countQuery = entityManager.createQuery(countBuilder.toString());
        long count = (long) countQuery.getSingleResult();

        Query query = entityManager.createQuery(queryBuilder.toString());

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        int totalPages = (int) Math.ceil((double) count / pageSize);

        return new Pagination<>(query.getResultList(), pageNumber - 1, pageSize, count, totalPages);

    }
}
