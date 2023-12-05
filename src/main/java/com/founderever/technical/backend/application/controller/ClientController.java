package com.founderever.technical.backend.application.controller;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ClientController {

       private final ClientService clientService;

        @PostMapping
        public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
            ClientResponse clientResponse=clientService.createClient(clientRequest);
            return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
        }

        @PostMapping("/{clientId}/add-message")
        public ClientResponse addMessageToClient(@PathVariable String clientId, @RequestBody MessageRequest messageRequest) {
            return clientService.addMessageToClient(clientId,messageRequest);
        }
        @PatchMapping("/{clientId}")
        public ClientResponse updateClient(@PathVariable String clientId, @RequestBody ClientRequest clientRequest) {
            return clientService.updateClient(clientId,clientRequest);
        }

        @GetMapping
        public List<ClientResponse> getAllClients() {
            return clientService.getAllClients();
        }
    }
