package br.com.builders.client.api.resource.v1.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@With
@Jacksonized
@Builder
public class ClientDTO {

    Long id;
    String name;
    LocalDate birthDate;
    Integer age;

}
