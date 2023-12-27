package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientResponse createNewClient(ClientRequest clientRequest);
    ClientResponse addNewMessageToClient(String clientId, MessageRequest messageRequest);
    Pagination<ClientResponse> getAllClients(int page, int size);
    void updateClient(String clientId, ClientRequest clientRequest);
}
