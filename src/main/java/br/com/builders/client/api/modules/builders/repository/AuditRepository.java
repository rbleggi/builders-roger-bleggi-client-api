package br.com.builders.client.api.modules.builders.repository;

import br.com.builders.client.api.modules.builders.entity.AuditClient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditClient, String> {

}