package com.founderever.technical.backend.domain.service.mapper;

import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.entities.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageResponse messageToMessageResponse(Message message);
    Message messageResponseToMessage(MessageResponse messageResponse);
    List<Message> messageResponsesToMessages(List<MessageResponse> messageResponses);

}
