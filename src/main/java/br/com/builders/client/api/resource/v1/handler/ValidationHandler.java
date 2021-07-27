package br.com.builders.client.api.resource.v1.handler;

import br.com.builders.client.api.resource.v1.dto.ErrorMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ValidationHandler {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    void exceptionHandler(ValidationException e) {
        throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e);
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorMessageDTO exceptionHandler(ConstraintViolationException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(e.getConstraintViolations()
                        .stream()
                        .map(objectError -> format("{0}: {1}", ((FieldError) objectError).getField(), (objectError).getMessage()))
                        .sorted()
                        .collect(Collectors.toList()))
                .error(e.getClass().getSimpleName())
                .status(BAD_REQUEST.value())
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorMessageDTO handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(e.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(objectError -> format("{0}: {1}", ((FieldError) objectError).getField(), (objectError).getDefaultMessage()))
                        .sorted()
                        .collect(Collectors.toList()))
                .error(e.getClass().getSimpleName())
                .status(BAD_REQUEST.value())
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
