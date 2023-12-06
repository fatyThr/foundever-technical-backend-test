package com.founderever.technical.backend.domain.service.impl;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.entities.Message;
import com.founderever.technical.backend.domain.repositories.MessageRepository;
import com.founderever.technical.backend.domain.service.ClientService;
import com.founderever.technical.backend.domain.service.MessageService;
import com.founderever.technical.backend.domain.service.mapper.ClientMapper;
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
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;



    @Override
    public MessageResponse createMessage(MessageRequest messageRequest) {
        log.info("Service Start Create message with messageRequest: {} ", messageRequest);
        Message message = Message.builder()
                .author(messageRequest.getAuthor())
                .content(messageRequest.getContent())
                .build();
        return messageMapper.messageToMessageResponse(messageRepository.save(message));
    }


    @Override
    public MessageResponse getMessageById(String messageId) {
        log.info("Get message with id: {} ", messageId);
        Optional<Message> message = messageRepository.findById(UUID.fromString(messageId));
        if (message.isEmpty()) {
            throw new TechnicalException("Message Not found with id "+messageId);
        }
        return messageMapper.messageToMessageResponse(message.get());

    }

}
