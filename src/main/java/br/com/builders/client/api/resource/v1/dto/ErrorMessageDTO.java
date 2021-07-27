package br.com.builders.client.api.resource.v1.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Value
@With
@Jacksonized
@Builder
public class ErrorMessageDTO {

    @Builder.Default
    LocalDateTime timestamp = now();
    Integer status;
    String error;
    List<String> message;
    String path;

}