package com.founderever.technical.backend.application.controller;

import com.founderever.technical.backend.application.request.ClientRequest;
import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.ClientResponse;
import com.founderever.technical.backend.domain.service.ClientService;
import com.founderever.technical.backend.infrastructure.exceptions.TechnicalException;
import com.founderever.technical.backend.infrastructure.utils.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        @Operation(
                summary = "Create Client REST API",
                description = "Create Message REST API is used to save Client"
        )
        @PostMapping
        public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
            ClientResponse clientResponse=clientService.createClient(clientRequest);
            return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
        }
        @Operation(
                summary = "ADD message to Client REST API",
                description = "add-message Rest API is used to add Message to client with id "
        )
        @PostMapping("/{clientId}/add-message")
        public ResponseEntity<ClientResponse> addMessageToClient(@PathVariable String clientId, @RequestBody MessageRequest messageRequest) {
             ClientResponse clientResponse=clientService.addMessageToClient(clientId,messageRequest);
            return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);

        }
        @Operation(
                summary = "Update Client REST API",
                description = "Update Client Rest API is used to  Update client with id and add reference client"
        )
        @PatchMapping("/{clientId}")
        public ResponseEntity<Void> updateClient(@PathVariable String clientId, @RequestBody ClientRequest clientRequest) {
            try {
                clientService.updateClient(clientId, clientRequest);
                return ResponseEntity.noContent().build();
            } catch (TechnicalException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @Operation(
                summary = "GET all Client REST API",
                description = "GET all Client Rest API is used to  select All client"
        )
        @GetMapping
        public Pagination<ClientResponse> getAllClients(@RequestParam("page") int page,
                                                        @RequestParam(name = "size", defaultValue = Integer.MAX_VALUE + "") int size) {
        Pageable pageable= PageRequest.of(page, size);
        return clientService.getAllClients(pageable);
        }
    }
