package br.com.builders.client.api.service;

import br.com.builders.client.api.helper.MessageHelper;
import br.com.builders.client.api.modules.builders.repository.ClientRepository;
import br.com.builders.client.api.modules.builders.repository.spec.ClientSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static br.com.builders.client.api.util.creator.ClientCreator.client;
import static br.com.builders.client.api.util.creator.ClientCreator.createClientCreateRequestDTOToBeSaved;
import static br.com.builders.client.api.util.creator.ClientCreator.createClientToBeUpdated;
import static br.com.builders.client.api.util.creator.ClientCreator.validClientDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService service;
    @Mock
    private ClientRepository repository;
    @Mock
    private MessageHelper messageHelper;

    @Test
    void findById_ReturnClient_WhenSuccessful() {
        when(repository.findById(client.getId())).thenReturn(Optional.of(client));
        assertEquals(validClientDTO, service.findById(client.getId()));
    }

    @Test
    void findById_ReturnError_WhenEmptyIsReturned() {
        when(repository.findById(client.getId())).thenReturn(Optional.empty());
        assertEquals(NOT_FOUND, assertThrows(ResponseStatusException.class, () -> service.findById(client.getId())).getStatus());
    }

    @Test
    void findAll_ReturnClients_WhenSuccessful() {
        when(repository.findAll(any(ClientSpecification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(client)));
        final var all = service.findAll(Optional.of(client.getName()), Optional.of(client.getBirthDate()), PageRequest.of(0, 5));
        assertEquals(1L, all.getTotalElements());
        assertEquals(validClientDTO, all.getContent().get(0));
    }

    @Test
    void save_CreateClient_WhenSuccessful() {
        final var clienteId = client.getId();
        when(repository.save(any())).thenReturn(client.withId(clienteId));
        assertEquals(validClientDTO, service.create(createClientCreateRequestDTOToBeSaved));
    }

    @Test
    void update_UpdateClient_WhenSuccessful() {
        when(repository.findById(validClientDTO.getId())).thenReturn(Optional.of(client));
        when(repository.save(createClientToBeUpdated())).thenReturn(client);
        assertEquals(validClientDTO, service.update(validClientDTO.getId(), createClientCreateRequestDTOToBeSaved));
    }

    @Test
    void update_ReturnError_WhenEmptyIsReturned() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertEquals(NOT_FOUND, assertThrows(ResponseStatusException.class,
                () -> service.update(validClientDTO.getId(), createClientCreateRequestDTOToBeSaved)).getStatus());
    }

    @Test
    void delete_RemovesClient_WhenSuccessful() {
        when(repository.findById(client.getId())).thenReturn(Optional.of(client));
        doNothing().when(repository).delete(client);
        service.delete(client.getId());
    }

    @Test
    void delete_ReturnError_WhenClientNotExist() {
        when(repository.findById(client.getId())).thenReturn(Optional.empty());
        assertEquals(NOT_FOUND, assertThrows(ResponseStatusException.class, () -> service.delete(client.getId())).getStatus());
    }

}