package com.founderever.technical.backend.domain.service.impl;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.MessageService;
import com.founderever.technical.backend.domain.service.mapper.MessageMapper;
import com.founderever.technical.backend.infrastructure.exceptions.TechnicalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    @Override
    public MessageResponse createNewMessage(MessageRequest messageRequest) {
        log.info("Service Create new massage MessageRequest :"+messageRequest);
        Message message=Message.builder()
                .author(messageRequest.getAuthor())
                .content(messageRequest.getContent())
                .build();
        Message createdMessage=messageRepository.save(message);
        return messageMapper.messageToMessageResponse(createdMessage);
    }

}
