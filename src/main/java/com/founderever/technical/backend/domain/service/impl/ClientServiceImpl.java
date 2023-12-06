package com.founderever.technical.backend.domain.service.impl;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.entities.Client;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.ClientRepository;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.ClientService;
import com.founderever.technical.backend.domain.service.mapper.ClientMapper;
import com.founderever.technical.backend.infrastructure.exceptions.TechnicalException;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final MessageRepository messageRepository;

    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        log.info("Create Client clientRequest: {}", clientRequest);
        List<Message> messages = getMessagesByIds(clientRequest.getMessagesIds());
        Client client = Client.builder()
                .clientName(clientRequest.getClientName())
                .messages(messages)
                .build();
        Client savedClient = clientRepository.save(client);
        messages.forEach(message -> message.setClient(savedClient));
        return clientMapper.clientToClientResponse(savedClient);
    }

    @Override
    public ClientResponse addMessageToClient(String clientId, MessageRequest messageRequest) {
        log.info("Add message to Client clientId: {} / messageRequest: {}", clientId, messageRequest);
        Client client = getClientById(UUID.fromString(clientId));
        Message message = Message.builder()
                .content(messageRequest.getContent())
                .author(messageRequest.getAuthor())
                .client(client)
                .build();
        messageRepository.save(message);
        client.getMessages().add(message);
        return clientMapper.clientToClientResponse(client);
    }

    @Override
    public Pagination<ClientResponse> getAllClients(Pageable pageable) {
        log.info("Get All Clients with page: {} size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Pagination<Client> pagesClient = clientRepository.findAllClients(pageable);
        List<ClientResponse> clientResponses = pagesClient.getContent().stream()
                .map(clientMapper::clientToClientResponse)
                .toList();
        return new Pagination<>(clientResponses, pagesClient.getPage(), pagesClient.getSize(), pagesClient.getTotalElements(), pagesClient.getTotalPages());
    }

    @Override
    public void updateClient(String clientId, ClientRequest clientRequest) {
        log.info("Update Client with id: {} / and data: {}", clientId, clientRequest);
        Client client = getClientById(UUID.fromString(clientId));
        client.setClientReference(clientRequest.getClientReference());
        clientRepository.save(client);
    }

     private List<Message> getMessagesByIds(List<String> messageIds) {
        List<Message> messages = new ArrayList<>();
        messageIds.forEach(messageId -> {
            Optional<Message> messageOptional = messageRepository.findById(UUID.fromString(messageId));
            messageOptional.ifPresent(messages::add);
        });
        return messages;
    }

    private Client getClientById(UUID clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new TechnicalException("Client not found with ID: " + clientId));
    }
}
