package com.founderever.technical.backend.application.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequest {

    private String author;
    private String content;
}
