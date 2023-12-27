package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.application.response.MessageResponse;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientRequest clientRequest;
    private ClientResponse clientResponse;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        buildClientRequest();
        buildClientResponse();
    }


    @Test
    void givenClientRequest_createNewClient_ReturnOk() {
        String messageId="37991141-c116-484a-aeec-31bc91ca6275";
        Client client=Client.builder()
                .clientName(clientRequest.getClientName())
                .messages(Collections.singletonList(Message.builder().id(UUID.fromString(messageId)).build())).build();
        when(messageRepository.findById(any())).thenReturn(Optional.of(new Message()));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.clientToClientResponse(any(Client.class))).thenReturn(clientResponse);
        ClientResponse result = clientService.createNewClient(clientRequest);

        assertNotNull(result);
        assertEquals(clientResponse.getClientName(), result.getClientName());
        assertEquals(clientResponse.getMessages().size(), result.getMessages().size());

        verify(messageRepository, times(1)).findById(UUID.fromString(messageId));
        verify(clientRepository, times(1)).save(any(Client.class));
        verify(clientMapper, times(1)).clientToClientResponse(any(Client.class));



    }

    private void buildClientRequest() {
        List<String> messageIds = new ArrayList<>();
        messageIds.add("37991141-c116-484a-aeec-31bc91ca6275");
        this.clientRequest = ClientRequest.builder()
                .clientName("John Doe")
                .messagesIds(messageIds)
                .build();

    }
    private void buildClientResponse() {
        List<MessageResponse> messages = new ArrayList<>();
        MessageResponse messageResponse=MessageResponse.builder()
                .id("37991141-c116-484a-aeec-31bc91ca6275").author("John Doe").content("Hello Dear!").build();
        messages.add(messageResponse);
        this.clientResponse = ClientResponse.builder()
                .clientName("John Doe")
                .messages(messages)
                .build();

    }
}
