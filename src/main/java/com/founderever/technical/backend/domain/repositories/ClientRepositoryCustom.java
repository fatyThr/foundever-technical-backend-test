package com.founderever.technical.backend.domain.repositories;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.domain.entities.Client;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import org.springframework.data.domain.Pageable;

public interface ClientRepositoryCustom {
     Pagination<Client> findByCriteres(ClientRequest clientRequest, Pageable pageable);

}
