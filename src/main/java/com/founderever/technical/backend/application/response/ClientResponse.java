package com.founderever.technical.backend.application.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;



@Data
@Builder
public class ClientResponse {

    private String id;
    private String clientName;
    private String clientReference;

    private List<MessageResponse> messages;

}


