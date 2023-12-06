package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.entities.Client;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.ClientRepository;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.impl.ClientServiceImpl;
import com.founderever.technical.backend.domain.service.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;
    private ClientRequest clientRequest;
    private Message message;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        buildClientRequest();
        buildMessage();
    }

    @Test
    public void testCreateClient() {

        when(messageRepository.findById(any(UUID.class))).thenReturn(Optional.of(message));
        Client mockedClient = Client.builder()
                .clientName(clientRequest.getClientName())
                .messages(List.of(message))
                .build();

        when(clientRepository.save(any(Client.class))).thenReturn(mockedClient);

        ClientResponse mockedClientResponse=ClientResponse.builder().build();
        when(clientMapper.clientToClientResponse(any(Client.class))).thenReturn(mockedClientResponse);
        ClientResponse response = clientService.createClient(clientRequest);
        verify(clientRepository).save(any(Client.class));
        verify(clientMapper).clientToClientResponse(any(Client.class));

        assertEquals(mockedClientResponse, response);
    }

    private void buildMessage() {
        this.message = Message.builder()
                .author("Author")
                .content("Content")
                .build();
     }

    private void buildClientRequest() {
        List<String> messageIds = new ArrayList<>();
        messageIds.add(UUID.randomUUID().toString());
        this.clientRequest=ClientRequest.builder()
                .clientName("John Doe")
                .messagesIds(messageIds)
                .build();

     }

    // Similar tests for other methods (addMessageToClient, getAllClients, updateClient) can be added here.
}
