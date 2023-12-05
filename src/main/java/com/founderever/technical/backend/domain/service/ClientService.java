package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;

import java.util.List;

public interface ClientService {
    ClientResponse createClient(ClientRequest clientRequest);

    ClientResponse addMessageToClient(String clientId, MessageRequest messageRequest);

    List<ClientResponse> getAllClients();


    ClientResponse updateClient(String clientId, ClientRequest clientRequest);

}
