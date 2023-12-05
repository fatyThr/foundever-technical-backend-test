package com.founderever.technical.backend.application.response;

import lombok.*;

@Builder
@Data
public class MessageResponse {
    private String id;
    private String author;
    private String content;
}
