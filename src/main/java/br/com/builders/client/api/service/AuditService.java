package br.com.builders.client.api.service;

import br.com.builders.client.api.event.ClientEvent;
import br.com.builders.client.api.modules.builders.entity.AuditClient;
import br.com.builders.client.api.modules.builders.repository.AuditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuditService {

    private final AuditRepository repository;

    public void audit(ClientEvent clientEvent) {
        repository.save(AuditClient.builder()
                .idClient(clientEvent.getIdClient())
                .operationType(clientEvent.getOperationType())
                .build());
    }

}