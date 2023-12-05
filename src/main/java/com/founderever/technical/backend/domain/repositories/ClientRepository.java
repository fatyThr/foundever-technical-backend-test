package com.founderever.technical.backend.domain.repositories;

import com.founderever.technical.backend.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
