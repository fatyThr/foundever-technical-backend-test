package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.impl.MessageServiceImpl;
import com.founderever.technical.backend.domain.service.mapper.MessageMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageServiceImpl messageService;

    private MessageResponse messageResponse;
    private MessageRequest messageRequest;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        buildMessageResponse();
        buildMessageRequest();

    }


    @Test
    public void givenMessageRequest_createNewMessage_ReturnMessageResponse(){
        Message message=Message.builder().id(UUID.fromString("37991141-c116-484a-aeec-31bc91ca6275")).author("John Doe").content("Hello Dear!")
                .build();
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(messageMapper.messageToMessageResponse(any(Message.class))).thenReturn(messageResponse);
        MessageResponse messageResponseResult=messageService.createNewMessage(messageRequest);
        verify(messageRepository,times(1)).save(any(Message.class));
        verify(messageMapper,times(1)).messageToMessageResponse(any(Message.class));
        assertEquals(messageResponse,messageResponseResult);
    }

    private void buildMessageResponse() {
        this.messageResponse=MessageResponse.builder()
                .id("37991141-c116-484a-aeec-31bc91ca6275").author("John Doe").content("Hello Dear!").build();
    }

    private void buildMessageRequest() {
        this.messageRequest=MessageRequest.builder()
                .author("John Doe").content("Hello Dear!").build();
    }


}
