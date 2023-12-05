package com.founderever.technical.backend.domain.repositories;

import com.founderever.technical.backend.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
