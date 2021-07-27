package br.com.builders.client.api.resource.v1.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Value
@With
@Jacksonized
@Builder
public class ClientCreateRequestDTO {

    @NotBlank
    String name;
    @NotNull @Past
    LocalDate birthDate;

}
