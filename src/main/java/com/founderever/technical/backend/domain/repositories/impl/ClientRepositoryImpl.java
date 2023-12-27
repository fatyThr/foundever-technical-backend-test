package com.founderever.technical.backend.domain.repositories.impl;

import com.founderever.technical.backend.application.request.ClientRequest;
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
public class ClientRepositoryImpl implements ClientRepositoryCustom {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Pagination<Client> findByCriteres(ClientRequest clientRequest, Pageable pageable) {
        Map<String,Object> params=new HashMap<>();
        StringBuilder queryBuilder=new StringBuilder(" Select client from Client client ");
        StringBuilder countBuilder=new StringBuilder(" Select count(client) from Client client ");
        StringBuilder whereBuilder=new StringBuilder(" where 1=1  ");

        if(clientRequest.getClientName()!=null){
            whereBuilder.append(" and client.clientName=:clientName");
            params.put("clientName",clientRequest.getClientName());

        }
        if(clientRequest.getClientReference()!=null){
            whereBuilder.append(" and client.clientReference=:clientReference");
            params.put("clientReference",clientRequest.getClientReference());

        }
        countBuilder.append(whereBuilder);
        Query countQuery=entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        long count= (long) countQuery.getSingleResult();
        queryBuilder.append(whereBuilder);
        Query query=entityManager.createQuery(queryBuilder.toString());
        params.forEach(query::setParameter);
        int numberPage=pageable.getPageNumber();
        query.setFirstResult(numberPage* pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        long totalPages= (long) Math.ceil((double) count /pageable.getPageSize());
        return new Pagination<Client>(query.getResultList(), numberPage, pageable.getPageSize(), count, totalPages);

    }
}
