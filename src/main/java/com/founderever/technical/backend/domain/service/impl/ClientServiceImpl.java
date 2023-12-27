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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final MessageRepository messageRepository;

    @Override
    public ClientResponse createNewClient(ClientRequest clientRequest) {
        log.info("Service create new Client payload : {}", clientRequest);
        List<Message> messages = getMessagesByIds(clientRequest.getMessagesIds());
        Client client = Client.builder()
                .clientName(clientRequest.getClientName())
                .messages(messages).build();
        Client clientCreated = clientRepository.save(client);
        return clientMapper.clientToClientResponse(clientCreated);
    }

    @Override
    public ClientResponse addNewMessageToClient(String clientId, MessageRequest messageRequest) {
        log.info("Service to add new message to Client {} payload : {}", clientId, messageRequest);
        Client client = getClientById(clientId);
        Message message = Message.builder()
                .author(messageRequest.getAuthor())
                .content(messageRequest.getContent()).build();
        client.getMessages().add(message);

        return clientMapper.clientToClientResponse(clientRepository.save(client));
    }

    @Override
    public Pagination<ClientResponse> getAllClients(int number, int size) {
        log.info("Service to get all Clients page {} size : {}", number, size);
        Pageable pageable = PageRequest.of(number, size);
        Page<Client> page = clientRepository.findAll(pageable);
        List<ClientResponse> clientResponses = page.getContent().stream().map(clientMapper::clientToClientResponse).toList();
        return new Pagination<>(clientResponses, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }

    @Override
    public void updateClient(String clientId, ClientRequest clientRequest) {
        log.info("Service to update Client {} payload : {}", clientId, clientRequest);
        Client client = getClientById(clientId);
        client.setClientReference(clientRequest.getClientReference());
        clientRepository.save(client);
    }

    private Client getClientById(String id) {
        return clientRepository.findById(UUID.fromString(id)).orElseThrow(() -> new TechnicalException("error.client.not.found", id));
    }

    private List<Message> getMessagesByIds(List<String> messageIds) {
        return messageIds.stream().map(messageId -> messageRepository.findById(UUID.fromString(messageId)).orElseThrow(() -> new TechnicalException("error.message.not.found", messageId))).toList();
    }
}
