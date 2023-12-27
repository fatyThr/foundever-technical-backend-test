package com.founderever.technical.backend.application.controller;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.service.ClientService;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(
        name = "CRUD REST APIs for Client Resource",
        description = "CRUD REST APIs - Create Client, Update Client,  Get All Client"
)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    @Operation(summary ="Create New Client",description = "REST API to create new Client")
    @PostMapping
    public ResponseEntity<ClientResponse> createNewClient(@RequestBody ClientRequest clientRequest) {
        log.info("Controller create new Client payload : {}",clientRequest);
        return new ResponseEntity<>(clientService.createNewClient(clientRequest), HttpStatus.CREATED);
    }
    @Operation(summary ="Add new Message to Client",description = "REST APi to Add new Message to Client")
    @PostMapping("/{clientId}/add-message")
    public ResponseEntity<ClientResponse> addNewMessageToClient(@PathVariable String clientId,@RequestBody MessageRequest messageRequest) {
        log.info("Controller to add new message to Client {} payload : {}",clientId,messageRequest);
        ClientResponse clientResponse=clientService.addNewMessageToClient(clientId,messageRequest);
        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }

    @Operation(summary ="Update Reference Client",description = "REST API to update reference client")
    @PatchMapping("/{clientId}")
    public ResponseEntity<Void> updateClient(@PathVariable String clientId,@RequestBody ClientRequest clientRequest) {
        log.info("Controller to update reference Client {} payload : {}",clientId,clientRequest);
        clientService.updateClient(clientId,clientRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary ="Get ALL Client",description = "REST APi to get ALL Client with page")
    @GetMapping
    public Pagination<ClientResponse> getAllClients(@RequestParam int page,@RequestParam int size) {
        log.info("Controller all clients page {} size : {}",page,size);
        return clientService.getAllClients(page,size);
    }
}