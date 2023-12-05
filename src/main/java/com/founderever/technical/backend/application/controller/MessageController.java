package com.founderever.technical.backend.application.controller;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest messageRequest) {
        log.info(" Controller Start create message messageRequest:{}",messageRequest);
        MessageResponse createdMessage = messageService.createMessage(messageRequest);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

}
