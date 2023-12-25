package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.impl.MessageServiceImpl;
import com.founderever.technical.backend.domain.service.mapper.MessageMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageMapper messageMapper;
    @InjectMocks
    private MessageServiceImpl messageService;
    private MessageRequest messageRequest;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        buildMessageRequest();
    }


    @Test
    public void testCreateMessage() {
        Message mockedMessage = Message.builder()
                .author(messageRequest.getAuthor())
                .content(messageRequest.getContent())
                .build();
        when(messageRepository.save(any(Message.class))).thenReturn(mockedMessage);

        MessageResponse mockedResponse =MessageResponse.builder().build();
        when(messageMapper.messageToMessageResponse(any(Message.class))).thenReturn(mockedResponse);
        MessageResponse response = messageService.createMessage(messageRequest);
        verify(messageRepository).save(any(Message.class));
        verify(messageMapper).messageToMessageResponse(any(Message.class));

        assertEquals(mockedResponse, response);
    }
    private void buildMessageRequest() {
        this.messageRequest = MessageRequest.builder()
                .author("John Doe")
                .content("")
                .build();
    }
}
