package com.founderever.technical.backend.domain.service.impl;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.entities.Client;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.ClientRepository;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.ClientService;
import com.founderever.technical.backend.domain.service.mapper.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;
    private final ClientMapper clientMapper;


    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        List<Message> messages = new ArrayList<>();
        if(!clientRequest.getMessagesIds().isEmpty()){
            clientRequest.getMessagesIds().forEach(messageId -> {
                Optional<Message> messageOptional = messageRepository.findById(UUID.fromString(messageId));
                messageOptional.ifPresent(messages::add);
            });
        }
        Client client=Client.builder()
                .clientName(clientRequest.getClientName())
                .messages(messages)
                .build();
        return clientMapper.clientToClientResponse(clientRepository.save(client));
    }

    @Override
    public ClientResponse addMessageToClient(String clientId, MessageRequest messageRequest) {
        Client client = clientRepository.findById(UUID.fromString(clientId)).orElseThrow();
        Message message=Message.builder()
                .author(messageRequest.getAuthor())
                .content(messageRequest.getContent())
                .build();
        client.getMessages().add(message);
        return clientMapper.clientToClientResponse(clientRepository.save(client));
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::clientToClientResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponse updateClient(String clientId, ClientRequest clientRequest) {
        Client client = clientRepository.findById(UUID.fromString(clientId)).orElseThrow();
        client.setClientReference(clientRequest.getClientReference());
        return clientMapper.clientToClientResponse(clientRepository.save(client));
    }
}
