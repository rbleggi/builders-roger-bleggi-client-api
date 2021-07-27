package br.com.builders.client.api.service;

import br.com.builders.client.api.helper.MessageHelper;
import br.com.builders.client.api.modules.builders.entity.Client;
import br.com.builders.client.api.modules.builders.repository.ClientRepository;
import br.com.builders.client.api.modules.builders.repository.spec.ClientSpecification;
import br.com.builders.client.api.resource.v1.dto.ClientCreateRequestDTO;
import br.com.builders.client.api.resource.v1.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.builders.client.api.exception.ErrorCodeEnum.ERROR_CLIENT_NOT_FOUND;
import static br.com.builders.client.api.util.mapper.MapperConstants.clientMapper;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository repository;
    private final MessageHelper messageHelper;

    public ClientDTO create(final ClientCreateRequestDTO requestDTO) {
        final var client = clientMapper.buildClient(requestDTO);
        final var savedClient = repository.save(client);
        return clientMapper.buildClientDTO(savedClient);
    }

    public Page<ClientDTO> findAll(final Optional<String> name, final Optional<LocalDate> birthDate, final Pageable pageable) {
        return repository.findAll(ClientSpecification.builder().name(name).birthDate(birthDate).build(), pageable)
                .map(clientMapper::buildClientDTO);
    }

    public ClientDTO findById(final Long clientId) {
        final var client = getById(clientId);
        return clientMapper.buildClientDTO(client);
    }

    public ClientDTO update(final Long id, final ClientCreateRequestDTO clientDTO) {
        final var client = getById(id);
        final var savedClient = repository.save(client.withName(clientDTO.getName()).withBirthDate(clientDTO.getBirthDate()));
        return clientMapper.buildClientDTO(savedClient);
    }

    public void delete(final Long id) {
        repository.delete(getById(id));
    }

    public Client getById(final Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error(messageHelper.get(ERROR_CLIENT_NOT_FOUND, id.toString()));
            return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_CLIENT_NOT_FOUND, id.toString()));
        });
    }

}