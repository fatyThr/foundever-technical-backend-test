package com.founderever.technical.backend.application.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ClientRequest {

    private String clientName;
    private String clientReference;

    private List<String> messagesIds;

}


