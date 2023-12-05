package com.founderever.technical.backend.domain.service.mapper;

import com.founderever.technical.backend.application.response.MessageResponse;
import com.founderever.technical.backend.domain.entities.Message;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageResponse messageToMessageResponse(Message message);

}
