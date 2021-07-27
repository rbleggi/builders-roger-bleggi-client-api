package br.com.builders.client.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_CLIENT_NOT_FOUND("error.client.not.found");

    private final String messageKey;
}
