package br.com.builders.client.api.event;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
public abstract class EventModel {

    @Builder.Default
    private final UUID eventId = UUID.randomUUID();
    @Builder.Default
    private final LocalDateTime created = LocalDateTime.now();

}