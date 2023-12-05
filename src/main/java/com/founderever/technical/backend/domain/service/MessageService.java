package com.founderever.technical.backend.domain.service;

import com.founderever.technical.backend.application.request.MessageRequest;
import com.founderever.technical.backend.application.response.MessageResponse;

public interface MessageService {
    MessageResponse createMessage(MessageRequest messageRequest);
}
