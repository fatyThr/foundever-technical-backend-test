package com.founderever.technical.backend.domain.service.mapper;

import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse clientToClientResponse(Client client);
    Client clientResponseToClient(ClientResponse clientResponse);

}
