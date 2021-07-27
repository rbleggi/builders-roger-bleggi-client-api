package br.com.builders.client.api.resource.v1;

import br.com.builders.client.api.resource.v1.dto.ClientCreateRequestDTO;
import br.com.builders.client.api.resource.v1.dto.ClientDTO;
import br.com.builders.client.api.service.ClientService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
@Tag(name = "Clients")
@Timed
public class ClientResource {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create client",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO create(@Valid @RequestBody ClientCreateRequestDTO client) {
        return service.create(client);
    }

    @GetMapping
    @Operation(summary = "Get clients",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Page.class)))})
    public Page<ClientDTO> findAll(@RequestParam(required = false) Optional<String> name,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DATE) Optional<LocalDate> birthDate,
                                   @RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size) {
        return service.findAll(name, birthDate, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search client by id",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client by id",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO update(@PathVariable Long id, @Valid @RequestBody ClientCreateRequestDTO client) {
        return service.update(id, client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client by id",
            responses = {@ApiResponse(responseCode = "204", description = "Client Successfully deleted")})
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}