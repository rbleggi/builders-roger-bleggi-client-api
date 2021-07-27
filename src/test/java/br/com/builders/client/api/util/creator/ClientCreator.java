package br.com.builders.client.api.util.creator;

import br.com.builders.client.api.modules.builders.entity.Client;
import br.com.builders.client.api.resource.v1.dto.ClientCreateRequestDTO;
import br.com.builders.client.api.resource.v1.dto.ClientDTO;

import java.time.LocalDate;

public class ClientCreator {

    public static final Client client = createClient();
    public static final ClientDTO clientDTOToBeUpdated = createClientDTOToBeUpdated();
    public static final ClientCreateRequestDTO createClientCreateRequestDTOToBeSaved = createClientCreateRequestDTOToBeSaved();
    public static final ClientDTO validClientDTO = createClientDTO();

    public static ClientDTO createClientDTO() {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .birthDate(LocalDate.now())
                .age(0)
                .build();
    }

    public static ClientCreateRequestDTO createClientCreateRequestDTOToBeSaved() {
        return ClientCreateRequestDTO.builder()
                .name(client.getName())
                .build();
    }

    public static Client createClient() {
        return Client.builder()
                .id(1L)
                .name("teste")
                .birthDate(LocalDate.now())
                .build();
    }

    public static ClientDTO createClientDTOToBeUpdated() {
        return ClientDTO.builder()
                .id(client.getId())
                .name("teste")
                .build();
    }

    public static Client createClientToBeUpdated() {
        return client.withName(clientDTOToBeUpdated.getName()).withBirthDate(clientDTOToBeUpdated.getBirthDate());
    }

}