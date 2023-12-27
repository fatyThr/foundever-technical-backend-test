package com.founderever.technical.backend.application.controller;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Message Resource",
        description = "CRUD REST APIs "
)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    @Operation(
            summary = "Create Message REST API",
            description = "Create Message REST API is used to save Message"
    )
    @PostMapping
    public ResponseEntity<MessageResponse> createNewMessage(@RequestBody MessageRequest messageRequest){
        log.info("Controller Create new massage MessageRequest :"+messageRequest);
        return new ResponseEntity<>(messageService.createNewMessage(messageRequest), HttpStatus.CREATED);
    }

}
