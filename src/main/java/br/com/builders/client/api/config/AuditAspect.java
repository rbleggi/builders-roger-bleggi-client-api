package br.com.builders.client.api.config;

import br.com.builders.client.api.event.ClientEvent;
import br.com.builders.client.api.resource.v1.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.builders.client.api.event.OperationTypeEnum.CREATE;
import static br.com.builders.client.api.event.OperationTypeEnum.DELETE;
import static br.com.builders.client.api.event.OperationTypeEnum.UPDATE;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(pointcut = "execution(* br.com.builders.client.api.service.ClientService.create(..))", returning = "clientDTO")
    public void auditCreateClient(JoinPoint joinPoint, ClientDTO clientDTO) {
        Optional.of(clientDTO).ifPresent(dto -> eventPublisher.publishEvent(ClientEvent.builder()
                .idClient(dto.getId())
                .operationType(CREATE).build()));
    }

    @AfterReturning(pointcut = "execution(* br.com.builders.client.api.service.ClientService.update(..))")
    public void auditUpdateClient(JoinPoint joinPoint) {
        eventPublisher.publishEvent(ClientEvent.builder().idClient((Long) joinPoint.getArgs()[0]).operationType(UPDATE).build());
    }

    @AfterReturning(pointcut = "execution(* br.com.builders.client.api.service.ClientService.delete(..))")
    public void auditDeleteClient(JoinPoint joinPoint) {
        eventPublisher.publishEvent(ClientEvent.builder().idClient((Long) joinPoint.getArgs()[0]).operationType(DELETE).build());
    }
}