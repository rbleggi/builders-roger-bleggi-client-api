package br.com.builders.client.api.event;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@SuperBuilder
public class ClientEvent extends EventModel {

    Long idClient;
    OperationTypeEnum operationType;

}