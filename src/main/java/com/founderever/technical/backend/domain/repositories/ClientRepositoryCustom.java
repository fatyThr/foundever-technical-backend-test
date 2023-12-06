package com.founderever.technical.backend.domain.repositories;

import com.founderever.technical.backend.domain.entities.Client;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import org.springframework.data.domain.Pageable;

public interface ClientRepositoryCustom {
    public Pagination<Client> findAllClients(Pageable pageable);
}
