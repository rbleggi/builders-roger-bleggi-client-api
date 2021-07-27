package br.com.builders.client.api.modules.builders.entity;

import br.com.builders.client.api.event.OperationTypeEnum;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@With
@Value
@Builder
@Document(collection = "auditClient")
public class AuditClient {

    @Id
    String id;

    @Builder.Default
    LocalDateTime createdDate = LocalDateTime.now();

    Long idClient;
    OperationTypeEnum operationType;

}
