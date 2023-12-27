package com.founderever.technical.backend.application.response;

import com.founderever.technical.backend.infrastructure.utils.enums.ChannelEnum;
import lombok.*;

@Builder
@Data
public class MessageResponse {
    private String id;
    private String author;
    private String content;
    private ChannelEnum channel;

}
