package com.founderever.technical.backend.domain.entities;

import com.founderever.technical.backend.infrastructure.utils.enums.ChannelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message implements Serializable {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private String content;
    private String author;
    @Enumerated(EnumType.STRING)
    private ChannelEnum channel;
}